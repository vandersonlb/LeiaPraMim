package br.com.fiap.leiapramim.service

import retrofit2.Retrofit

class TTSClient {

    private val URL = "https://api.voicerss.org/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .build()

    fun getTTSService(): TTSService {
        return retrofitFactory.create(TTSService::class.java)
    }
}
