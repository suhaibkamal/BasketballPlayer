package com.petraride.basketplayers

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.petraride.domain.model.color
import com.petraride.domain.model.image
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlayerDetailsScreen(viewModel: PlayersViewModel= koinViewModel<PlayersViewModel>(), navController: NavController, itemId: String?) {



    LaunchedEffect(Unit) {
        viewModel.loadPlayer(itemId?.toInt()?:0)
    }
    Scaffold(topBar = { TopAppBar(title = { Text("Player Details") },

        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        ) }) {
        paddings->
        PlayerDetails(viewModel,paddings)
    }
}


@Composable
fun PlayerDetails(
    viewModel: PlayersViewModel,
    paddings: PaddingValues
) {

    val player by viewModel.player.collectAsState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddings),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        player?.let {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(2.5f)
                    .background(Color(player?.color()?:0xFF2196F3), RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        model = player?.image(),
                        contentDescription = "Player Image",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = player?.firstName + " " + player?.lastName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Team: ${player?.team?.name ?: ""}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Country: ${player?.country}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White

                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
                    .verticalScroll(rememberScrollState()) // Enables scrolling
                    .padding(start = 10.dp, end = 10.dp).weight(4f),


            ){

                Spacer(modifier = Modifier.height(16.dp))


                infoCell("Player Number", player?.jerseyNumber?:"",Color(player?.color()?:0xFF2196F3))


                Spacer(modifier = Modifier.height(8.dp))


                infoCell("Player Weight", player?.weight?:"",Color(player?.color()?:0xFF2196F3))


                Spacer(modifier = Modifier.height(4.dp))


                infoCell("Player Height", player?.height?:"",Color(player?.color()?:0xFF2196F3))

                Spacer(modifier = Modifier.height(4.dp))


                infoCell("Player college", player?.college?:"",Color(player?.color()?:0xFF2196F3))



                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Team Information",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(player?.color()?:0xFF2196F3)
                )

                Spacer(modifier = Modifier.height(16.dp))



                infoCell("Team name", player?.team?.fullName?:"",Color(player?.color()?:0xFF2196F3))


                Spacer(modifier = Modifier.height(4.dp))


                infoCell("Team conference", player?.team?.conference?:"",Color(player?.color()?:0xFF2196F3))


                Spacer(modifier = Modifier.height(4.dp))

                infoCell("Team city", player?.team?.city?:"",Color(player?.color()?:0xFF2196F3))

                Spacer(modifier = Modifier.height(4.dp))


                infoCell("Team division", player?.team?.division?:"",Color(player?.color()?:0xFF2196F3))

            }
        }?: CircularProgressIndicator()

    }
}