package com.isna.appinformationcovid.data.service

import com.isna.appinformationcovid.data.model.GetAllCountryCases
import com.isna.appinformationcovid.data.model.GetAllData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("covid-19/countries?sort=cases")
    suspend fun getAllDataCovid(): List<GetAllCountryCases>

    @GET("covid-19/countries/{country}")
    suspend fun getDataCovidById(@Path("country") country: String): GetAllCountryCases

    @GET("covid-19/all")
    suspend fun getAllData(): GetAllData
}