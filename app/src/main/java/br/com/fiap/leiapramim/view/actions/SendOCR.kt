package br.com.fiap.leiapramim.view.actions

import android.net.Uri
import android.util.Log
import br.com.fiap.leiapramim.model.OCRModel
import br.com.fiap.leiapramim.service.OCRClient
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

fun SendOCR(uri: Uri) {

    val file = File(uri.path ?: "")
    val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

    val call = OCRClient().getOCRService().postImage(body)

    call.enqueue(object : Callback<OCRModel> {
        override fun onResponse(
            call: Call<OCRModel>,
            response: Response<OCRModel>
        ) {

            val ocrResponse = response.body()

            if (ocrResponse != null && ocrResponse?.ocrExitCode != 1)
                throw Exception("Falha na leitura da imagem")

            if (ocrResponse?.parsedResults?.get(0)?.parsedText == "") {
                Log.i("FIAP", "Não achou texto")
            } else {
                Log.i("FIAP", "ACHOU texto")
                Log.i("FIAP", "${response.body()?.parsedResults?.get(0)?.parsedText!!}")
            }

        }

        override fun onFailure(call: Call<OCRModel>, t: Throwable) {
            Log.e("FIAP", "$t")
        }

    })
}