package br.com.fiap.leiapramim.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TTSClient {

    private val URL = "https://api.voicerss.org/?key=dbb3f4b4345f46fdbc381da41e02628a&"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
//        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getTTSService(): TTSService {
        return retrofitFactory.create(TTSService::class.java)
    }
}
