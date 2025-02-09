package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color(0xFFD2E8D4))
    ) {
        Image (
            painter = painterResource(R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 150.dp)
        )
        Text(
            text = "Shirak Ogannisyan",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = "Android Developer Extraordinaire",
            fontSize = 18.sp,
            color = Color(0xFF006c38),
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 130.dp)
                .padding(end = 50.dp)
                .padding(bottom = 8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_phone_24),
                tint = Color(0xFF006c38),
                contentDescription = null
            )
            Text(
                text = "+7 (925) 304 9293",
                fontSize = 20.sp,
                color = Color(0xFF006c38),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(end = 95.dp)
                .padding(bottom = 8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_share_24),
                tint = Color(0xFF006c38),
                contentDescription = null
            )
            Text(
                text = "@AndroidDev",
                fontSize = 20.sp,
                color = Color(0xFF006c38),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(bottom = 8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_mail_24),
                tint = Color(0xFF006c38),
                contentDescription = null
            )
            Text(
                text = "shirak2508@gmail.com",
                fontSize = 20.sp,
                color = Color(0xFF006c38),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}