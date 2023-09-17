package br.com.fiap.leiapramim.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.viewmodel.NavigationViewModel
import br.com.fiap.leiapramim.view.components.BottomNavigation
import br.com.fiap.leiapramim.view.components.loading
import br.com.fiap.leiapramim.viewmodel.ReadImageListViewModel

@Composable
fun GalleryScreen(
    navController: NavHostController,
    navigationViewModel: NavigationViewModel
) {
    Column {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .wrapContentSize(Alignment.Center)
                .weight(1f)
        ) {

        }
        Row() {
            BottomNavigation(navController, navigationViewModel)
        }
    }
}
