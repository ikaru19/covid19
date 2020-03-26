package com.ikaru.covid19

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {

    fun create(): CaseServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.kawalcovid19.id/case/")
            .build()
        return retrofit.create(CaseServices::class.java)
    }
}
