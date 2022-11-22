package com.example.projet_dev_mobile.adapter

import com.example.projet_dev_mobile.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {

    @GET("api/stations")
    suspend fun getStations(): Response<List<Station>>

}