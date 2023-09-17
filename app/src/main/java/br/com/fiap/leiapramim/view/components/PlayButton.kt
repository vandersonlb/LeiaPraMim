package br.com.fiap.leiapramim.view.components

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.R
import java.io.File

@Composable
fun playButton(audioFile: File, mediaPlayer: MediaPlayer) {

    Row(
        Modifier
            .fillMaxSize()
            .background(Color(0x0, 0x0, 0x0, 0x4D)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            Modifier.size(256.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.play),
                contentDescription = "Botão de play",
                tint = Color.Unspecified,
            )
        }
    }

    mediaPlayer.setDataSource(audioFile.path)
    mediaPlayer.prepare()
    mediaPlayer.start()

//    DisposableEffect(Unit) {
//        onDispose {
//            mediaPlayer.release()
//        }
//    }
}

