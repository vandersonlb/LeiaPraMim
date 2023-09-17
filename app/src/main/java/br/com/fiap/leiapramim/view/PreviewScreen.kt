package br.com.fiap.leiapramim.view

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.R
import br.com.fiap.leiapramim.view.actions.sendOCR
import br.com.fiap.leiapramim.view.actions.sendTTS
import coil.compose.rememberImagePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun PreviewScreen(navController: NavHostController, uriString: String) {

    val context = LocalContext.current
    val uri = Uri.parse(uriString)

    Box {
        Image(
            painter = rememberImagePainter(data = uri),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

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

                        GlobalScope.launch(Dispatchers.IO) {
                            performAsyncOperations(uri, context)
                        }

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

suspend fun performAsyncOperations(uri: Uri, context: Context) {
    val ocrResponseDeferred = coroutineScope {
        async(Dispatchers.IO) {
            sendOCR(uri)
        }
    }

    val ttsResponseDeferred = coroutineScope {
        async(Dispatchers.IO) {
            val ocrResponse = ocrResponseDeferred.await()
            sendTTS(uri, context, ocrResponse)
        }
    }

    // Aguarda o resultado da função sendTTS e trata o resultado, se necessário
    val audioFile = ttsResponseDeferred.await()
//    Log.i("FIAP", "QUERO ESPERAR ${ttsResponseDeferred.await()}")
    // Faça o que precisar com ttsResponse


    val mediaPlayer = MediaPlayer()
    mediaPlayer.setDataSource(audioFile?.path)
    mediaPlayer.prepare()
    mediaPlayer.start()
}