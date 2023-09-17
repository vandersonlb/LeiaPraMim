package br.com.fiap.leiapramim.view.actions

import android.content.Context
import android.net.Uri
import br.com.fiap.leiapramim.model.ReadImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.time.LocalDateTime

suspend fun asyncCallApis(uri: Uri, context: Context): ReadImage {

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

    val audioFile = ttsResponseDeferred.await()

    return ReadImage(uri.path, audioFile?.path, LocalDateTime.now())


}