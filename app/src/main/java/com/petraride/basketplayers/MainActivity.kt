package com.petraride.basketplayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.petraride.basketplayers.ui.theme.BasketPlayersTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasketPlayersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    playersScreen(innerPadding = innerPadding)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun playersScreen(viewModel: PlayersViewModel = getViewModel(),innerPadding: PaddingValues) {
    val players by viewModel.players.collectAsState()

    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(players){
            player ->
            Text(text = player.firstName, fontSize = 20.sp)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadPlayers()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasketPlayersTheme {
        Greeting("Android")
    }
}