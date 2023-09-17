package br.com.fiap.leiapramim.view

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.leiapramim.service.TTSClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

@Composable
fun TocaAudio() {

    val context = LocalContext.current

    val mediaPlayer = MediaPlayer()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {

                var call = TTSClient().getTTSService().getTeste("Testando 1 2 3")

                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        if (response.isSuccessful) {
                            val audioBytes = response.body()?.bytes()

                            if (audioBytes != null) {
//                                val audioFile = File(Environment.getExternalStorageDirectory(),"audio_temp.mp3")
                                val audioFile = File(context.filesDir, "audio_temp.mp3")
                                FileOutputStream(audioFile).use { outputStream ->
                                    outputStream.write(audioBytes)
                                }

                                // Configura o MediaPlayer
//                                mediaPlayer.setDataSource(audioFile.path)
//                                mediaPlayer.prepare()
//                                mediaPlayer.start()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("teste", "Erro ao fazer a requisição: ${t.message}")
                    }

                })

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "TOCAR ÁUDIO")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release() // Libera os recursos quando o composable é removido
        }
    }
}
