package com.example.a42userinfo.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.a42userinfo.domain.model.GetUserDataModel
import com.example.a42userinfo.domain.model.GetUserProjectModel
import com.example.a42userinfo.domain.model.SkillModel
import com.example.a42userinfo.ui.components.HomeTopBar
import com.example.a42userinfo.ui.components.ListElements
import com.example.a42userinfo.ui.components.LoadComponents

@Composable
fun HomeScreen(
    onLogOutClick: () -> Unit = {}, code: String?, homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = code) {
        if (code != null) {
            homeViewModel.getToken(code)
        }
    }

    when (uiState) {
        UiState.LOADING -> {
            LoadComponents()
        }

        UiState.SUCCESS -> {
            HomeLayout(onLogOutClick)
        }

        UiState.ERROR -> {
            Text("Error en la autenticación")
        }
    }
}

@Composable
fun HomeLayout(
    onLogOutClick: () -> Unit = {}
) {
    Scaffold(containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.primary,
        topBar = {
            HomeTopBar(title = "Home", onLogOutClick = onLogOutClick)
        },

        content = { innerPadding ->
            HomeBody(modifier = Modifier.padding(innerPadding))
        })
}

@Composable
fun HomeBody(modifier: Modifier = Modifier) {
    val skills = listOf(
        SkillModel("web", level = "5"),
        SkillModel("c", level = "10"),
    )
    val projects = listOf(
        GetUserProjectModel(name = "ft_transcendence", status = "success", finalMark = "120"),
        GetUserProjectModel(name = "ft_irc", status = "success", finalMark = "100"),
        GetUserProjectModel(name = "Swifty Companion", status = "failed", finalMark = "0")
    )
    Column(
        modifier = modifier.padding(10.dp)
    ) {
        UserCard(
            GetUserDataModel(
                userImg = "https://images.freeimages.com/images/large-previews/3cb/the-treasure-1203251.jpg",
                "asolano-",
                "asolano-@Student.42Malaga.com",
                level = "15",
                evPoints = "0"
            )
        )
        ListElements(elements = skills, title = "Skills") { skill ->
            Text(
                text = skill.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${TextOverflow.Ellipsis}: ${skill.level}%",
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
                text = "${project.status}: ${project.finalMark}%",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun UserCard(userInfo: GetUserDataModel) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
    ) {
        Row(
            modifier = Modifier.padding(all = 10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(userInfo.userImg),
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
                    text = userInfo.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = userInfo.email,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Level: " + userInfo.level,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Evaluation points: " + userInfo.evPoints,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeLayout()
}