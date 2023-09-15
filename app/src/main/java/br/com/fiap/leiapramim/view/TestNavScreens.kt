package br.com.fiap.leiapramim.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.model.NavigationViewModel
import br.com.fiap.leiapramim.route.NavigationItem
import br.com.fiap.leiapramim.view.components.BottomNavigation

@Composable
fun HomeScreen(navController: NavHostController, navigationViewModel: NavigationViewModel) {

    Column {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .wrapContentSize(Alignment.Center)
                .weight(1f)
        ) {
            Text(
                text = "Home",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
        Row() {

            val navItems = listOf(
                NavigationItem.Home,
                NavigationItem.Camera,
                NavigationItem.Gallery
            )
            BottomNavigation(navController, navItems, navigationViewModel)
        }
    }
}

@Composable
fun CameraScreen(navController: NavHostController, navigationViewModel: NavigationViewModel) {
    Column {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .wrapContentSize(Alignment.Center)
                .weight(1f)
        ) {
            Text(
                text = "Camera",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
        Row() {

            val navItems = listOf(
                NavigationItem.Home,
                NavigationItem.Camera,
                NavigationItem.Gallery
            )
            BottomNavigation(navController, navItems, navigationViewModel)
        }
    }
}

@Composable
fun GalleryScreen(navController: NavHostController, navigationViewModel: NavigationViewModel) {
    Column {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .wrapContentSize(Alignment.Center)
                .weight(1f)
        ) {
            Text(
                text = "Galeria",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
        Row() {

            val navItems = listOf(
                NavigationItem.Home,
                NavigationItem.Camera,
                NavigationItem.Gallery
            )
            BottomNavigation(navController, navItems, navigationViewModel)
        }
    }
}
