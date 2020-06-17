package com.sumit.jet2travel.Interfaces

import com.sumit.jet2travel.DataModel
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("jet2/api/v1/blogs?page=1&limit=10")
    fun getProjectList(): Call<List<DataModel>>


}