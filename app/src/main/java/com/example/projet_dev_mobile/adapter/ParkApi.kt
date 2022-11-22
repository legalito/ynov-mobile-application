package com.example.projet_dev_mobile.adapter

import com.example.projet_dev_mobile.model.Park
import retrofit2.Response
import retrofit2.http.GET

interface ParkApi {

    @GET("api/parks")
    suspend fun getParks(): Response<List<Park>>

}