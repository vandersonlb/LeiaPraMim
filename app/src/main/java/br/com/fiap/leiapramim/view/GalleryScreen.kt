package br.com.fiap.leiapramim.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.R
import br.com.fiap.leiapramim.ui.theme.White
import br.com.fiap.leiapramim.view.actions.sendTTS
import br.com.fiap.leiapramim.viewmodel.NavigationViewModel
import br.com.fiap.leiapramim.view.components.BottomNavigation
import br.com.fiap.leiapramim.view.components.loading
import br.com.fiap.leiapramim.view.components.playButton

@Composable
fun GalleryScreen(navController: NavHostController, navigationViewModel: NavigationViewModel) {

    Box {

        Column {
//            playButton()
            loading()
        }

        Row() {
            BottomNavigation(navController, navigationViewModel)
        }
    }
}
