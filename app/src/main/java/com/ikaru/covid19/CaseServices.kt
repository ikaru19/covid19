package com.ikaru.covid19

import retrofit2.Call
import retrofit2.http.GET

interface CaseServices {
    @GET("summary")
    fun getPosts(): Call<CaseModel>
}