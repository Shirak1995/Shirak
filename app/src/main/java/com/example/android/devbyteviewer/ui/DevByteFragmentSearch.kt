/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.devbyteviewer.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.devbyteviewer.R
import com.example.android.devbyteviewer.databinding.DevbyteItemBinding
import com.example.android.devbyteviewer.databinding.FragmentDevByteBinding
import com.example.android.devbyteviewer.domain.DevByteVideo
import com.example.android.devbyteviewer.viewmodels.DevByteViewModel

class DevByteFragmentSearch : Fragment() {
    private val viewModel: DevByteViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, DevByteViewModel.Factory(activity.application))
            .get(DevByteViewModel::class.java)
    }

    private var viewModelAdapter: DevByteAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Наблюдаем за списком видео
        viewModel.filteredPlaylist.observe(viewLifecycleOwner, Observer<List<DevByteVideo>> { videos ->
            videos?.apply {
                viewModelAdapter?.videos = videos
            }
        })

        // Наблюдаем за событием сетевой ошибки
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentDevByteBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dev_byte,
            container,
            false)

        // Устанавливаем LifecycleOwner, чтобы DataBinding мог наблюдать за LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModelAdapter = DevByteAdapter(VideoClick { video ->
            val packageManager = context?.packageManager ?: return@VideoClick

            var intent = Intent(Intent.ACTION_VIEW, video.launchUri)
            if(intent.resolveActivity(packageManager) == null) {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.url))
            }

            startActivity(intent)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        // Инициализируем SearchView
        val searchView = binding.root.findViewById<SearchView>(R.id.search_view) // Убедитесь, что вы добавили SearchView в ваш layout
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.updateSearchQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText ?: "")
                return true
            }
        })

        return binding.root
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private val DevByteVideo.launchUri: Uri
        get() {
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }
}



 