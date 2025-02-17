package com.petraride.basketplayers

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.petraride.domain.model.color
import com.petraride.domain.model.image
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayersScreen(
    viewModel: PlayersViewModel = koinViewModel<PlayersViewModel>(),
    navController: NavController
) {
    val players = viewModel.pagedPlayers.collectAsLazyPagingItems()
    val context = LocalContext.current
    LaunchedEffect(key1 = players.loadState) {
        if (players.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (players.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Scaffold(topBar = { TopAppBar(title = { Text("Players") }) }, content = {
        innerPadding ->
        when (players.loadState.refresh) {
            is LoadState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is LoadState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error loading data!", color = Color.Red)
                }
            }
            else->{
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(9.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(players.itemCount) {
                        val player = players[it]
                        if (player != null) {
                            PlayerCardView(player.image()?:"",
                                player.firstName + "" + player.lastName,
                                player.team.name,
                                Color(player.color()),
                                onclick = {
                                    navController.navigate("details/${player.id}")
                                }
                            )

                        }

                    }

                    players.apply {
                        when {
                            loadState.append is LoadState.Loading -> {
                                item { CircularProgressIndicator() }
                                item {  Spacer(modifier = Modifier.height(32.dp)) }
                            }

                            loadState.append is LoadState.Error -> {
                                item {
                                    Text("Error loading more items", color = Color.Red)
                                    Spacer(modifier = Modifier.height(32.dp))
                                }
                            }
                        }
                    }
                }

            }}
    })




}
