package br.com.fiap.leiapramim.service

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface OCRService {

    @GET("parse/imageurl?apikey=K84662716688957&url=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfxwPiEBzoLgi9CXnFe5gLjBF1RhFhPWbv4-7_t3q-&s")
    fun getTeste(): Call<ResponseBody>

    @Headers("apikey: K84662716688957")
    @Multipart
    @POST("parse/image")
    fun postImage(
        @Part image: MultipartBody.Part,
        @Part("language") language: RequestBody = RequestBody.create(MediaType.parse("text/plain"),"por")
    ): Call<ResponseBody>

}
