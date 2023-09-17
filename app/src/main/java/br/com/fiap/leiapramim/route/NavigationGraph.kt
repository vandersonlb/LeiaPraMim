package br.com.fiap.leiapramim.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.leiapramim.viewmodel.NavigationViewModel
import br.com.fiap.leiapramim.view.CameraScreen
import br.com.fiap.leiapramim.view.GalleryScreen
import br.com.fiap.leiapramim.view.HomeScreen
import br.com.fiap.leiapramim.view.PreviewScreen
import br.com.fiap.leiapramim.viewmodel.ReadImageListViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    navigationViewModel: NavigationViewModel,
) {

    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) { HomeScreen(navController, navigationViewModel) }
        composable(NavigationItem.Camera.route) { CameraScreen(navController, navigationViewModel) }
        composable(NavigationItem.Gallery.route) { GalleryScreen(navController, navigationViewModel) }

        composable(route = "${NavigationItem.Preview.route}/{uri}") {
            PreviewScreen(navController, it.arguments?.getString("uri")!!)
        }
    }

}