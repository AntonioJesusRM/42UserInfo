package com.example.a42userinfo.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.a42userinfo.R
import com.example.a42userinfo.ui.components.HomeTopBar
import com.example.a42userinfo.ui.components.ListElements
import com.example.a42userinfo.ui.components.LoadComponents
import com.example.a42userinfo.ui.theme.UserInfoTheme

@Composable
fun HomeScreen(
    onLogOutClick: () -> Unit = {}, code: String?, homeViewModel: HomeViewModel = hiltViewModel()
) {
    val userUiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = code) {
        if (code != null) {
            homeViewModel.getToken(code)
        }
    }
    if (userUiState.isLoading) {
        LoadComponents()
    } else if (userUiState.error != null) {
        Text("Error: ${userUiState.error}")
    } else {
        Log.d(TAG, "%> DATA: $userUiState")
        HomeLayout(onLogOutClick, userUiState)
    }
}

@Composable
fun HomeLayout(
    onLogOutClick: () -> Unit = {}, userUiState: UserUiState
) {
    UserInfoTheme(userUiState.coalition) {
        Scaffold(containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
            topBar = {
                HomeTopBar(title = "Home", onLogOutClick = onLogOutClick)
            },

            content = { innerPadding ->
                HomeBody(modifier = Modifier.padding(innerPadding), userUiState)
            })
    }
}

@Composable
fun HomeBody(modifier: Modifier = Modifier, userUiState: UserUiState) {
    val skills = userUiState.skills
    val projects = userUiState.listProjects
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        UserCard(userUiState)
        ListElements(elements = skills, title = "Skills") { skill ->
            Text(
                text = skill.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${TextOverflow.Ellipsis}: ${skill.level}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        ListElements(elements = projects, title = "Projects") { project ->
            Text(
                text = project.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (project.finalMark == "null") {
                    project.status
                } else {
                    "${project.status}: ${project.finalMark}%"
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = when (project.status) {
                    "finished" -> colorResource(R.color.green)
                    "in_progress" -> colorResource(R.color.orange)
                    else -> colorResource(R.color.red)
                }
            )
        }
    }
}

@Composable
fun UserCard(userUiState: UserUiState) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(userUiState.imgCoalition)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                ),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 120.dp)
                    .size(120.dp)
                    .alpha(0.5f)
            )

            Row(
                modifier = Modifier.padding(all = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(userUiState.imgUser),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(
                            1.5.dp, MaterialTheme.colorScheme.primary, RectangleShape
                        )
                        .size(120.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = userUiState.login,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = userUiState.email,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Level: " + userUiState.level,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Evaluation points: " + userUiState.evPoints,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeLayout(
        userUiState = UserUiState(
            login = "Asolano-",
            email = "asolano-@student42.Malaga.com",
            imgCoalition = "https://cdn.intra.42.fr/coalition/image/274/Icono_Hades_2__3_.svg",
            evPoints = "15",
            level = "11",
            coalition = "void"
        )
    )
}