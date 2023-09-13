package br.com.fiap.leiapramim.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface TTSService {

    @GET("?key=dbb3f4b4345f46fdbc381da41e02628a&hl=pt-br&src=Com toda a sua força e determinação faça o L")
    fun getTeste(): Call<ResponseBody>

}