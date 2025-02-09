package com.example.thirtydaysofcinema

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysofcinema.data.Movie
import com.example.thirtydaysofcinema.data.movies
import com.example.thirtydaysofcinema.ui.theme.ThirtyDaysOfCinemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysOfCinemaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CinemaApp()
                }
            }
        }
    }
}

@Composable
fun CinemaApp() {
    Scaffold(
        topBar = {
            CinemaTopAppBar()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(movies) {
                MovieItem(movie = it)
            }
        }
    }
}

@Composable
fun CinemaTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Green),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun MovieItem(movie: Movie, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .animateContentSize (
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                //.background(color = color)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(movie.day),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = stringResource(movie.name),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(movie.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable { expanded = !expanded },
                    contentScale = ContentScale.Crop
                )

                if (expanded) {
                    MovieDescription(movie.description)
                }
            }
        }
    }
}

@Composable
fun MoviePoster(
    @DrawableRes poster: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(poster),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { onClick },
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MovieDescription(@StringRes description: Int, modifier: Modifier = Modifier) {
    Row {
        Text(
            text = stringResource(description),
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h6
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThirtyDaysOfCinemaTheme {
        CinemaApp()
    }
}