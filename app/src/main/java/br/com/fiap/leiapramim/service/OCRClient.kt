package br.com.fiap.leiapramim.service

import retrofit2.Retrofit

class OCRClient {

    private val BASE_URL = "https://api.ocr.space/"

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getOCRService(): OCRService {
        return retrofit.create(OCRService::class.java)
    }

}