package br.com.fiap.leiapramim.view

import android.os.Environment
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import br.com.fiap.leiapramim.R
import br.com.fiap.leiapramim.service.OCRClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Composable
fun PocOcr() {

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .wrapContentSize(Alignment.Center)
    ) {

        Image(painter = painterResource(id = R.drawable.foto), contentDescription = "null")

        Button(onClick = {

            Log.i("teste", "Botão clicado")


//            val picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//            val picturesDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val picturesDirectory = context.filesDir

            val file = File(picturesDirectory, "foto_teste.jpg")
            val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

            val call = OCRClient().getOCRService().postImage(body)

            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.i("teste", "${response.body()?.string()}")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("teste", "$t")
                }

            })


        }) {
            Text(text = "Mandar Imagem")

        }
    }
}


