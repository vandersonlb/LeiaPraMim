package br.com.fiap.leiapramim.view

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.R
import br.com.fiap.leiapramim.model.ReadImage
import br.com.fiap.leiapramim.view.actions.asyncCallApis
import br.com.fiap.leiapramim.view.components.loading
import br.com.fiap.leiapramim.view.components.playButton
import coil.compose.rememberImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDateTime

@Composable
fun PreviewScreen(navController: NavHostController, uriString: String) {

    val context = LocalContext.current
    val uri = Uri.parse(uriString)
    var loadingState by remember { mutableStateOf(false) }
    var audioReady by remember { mutableStateOf(false) }
    var readImage by remember { mutableStateOf(ReadImage("", "", LocalDateTime.now())) }

    Box {
        Image(
            painter = rememberImagePainter(data = uri),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (!loadingState && !audioReady) {

            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier.size(220.dp, 100.dp)
                ) {
                    val columnModifier = Modifier
                        .weight(1f)
//                    .fillMaxSize()
//                    .background(Color.Red)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                    Column(columnModifier) {
                        ButtonPreview(
                            R.drawable.cancel,
                            "Botão de cancelar"
                        ) {
                            navController.popBackStack()
                        }
                    }
                    Column(columnModifier) {
                        ButtonPreview(
                            R.drawable.done,
                            "Botão de aceitar"
                        ) {

                            loadingState = !loadingState

                            GlobalScope.launch(Dispatchers.IO) {
                                readImage = asyncCallApis(uri, context)
                                loadingState = !loadingState
                                audioReady = !audioReady
                            }

                        }
                    }
                }
            }

        }

        if (loadingState && !audioReady) {
            Column {
                loading()
            }
        }
        if (audioReady) {

            val mediaPlayer = MediaPlayer()

            Column {
                playButton(File(readImage.audioPath), mediaPlayer)

                mediaPlayer.setOnCompletionListener {
                    mediaPlayer.reset()
                    mediaPlayer.release()
                    audioReady = !audioReady
                    Log.i("FIAP", "Terminou o audio")
                }

                DisposableEffect(Unit) {
                    onDispose {
                        mediaPlayer.release()
                    }
                }

            }
        }
    }
}

@Composable
fun ButtonPreview(icon: Int, description: String, action: () -> Unit) {

    IconButton(
        onClick = { action() },
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
            tint = Color.Unspecified
        )
    }
}