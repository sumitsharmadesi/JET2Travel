package com.sumit.jet2travel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sumit.jet2travel.Clients.APIClient
import com.sumit.jet2travel.Interfaces.ApiInterface
import com.sumit.jet2travel.Model.DataFetch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var dataList: List<DataModel> = ArrayList()
    private val mutablePostList: MutableLiveData<List<DataModel>> = MutableLiveData()

        fun getProjectList(): LiveData<List<DataModel>>? {
        var apiServices = APIClient.client.create(ApiInterface::class.java)
        val call = apiServices.getProjectList()


        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {

                dataList = response.body()!!;

                    mutablePostList.postValue(dataList)


            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                Log.d("ERROR : ", " ")
                try{
                    val pref: SharedPreferences = getApplication<Application>().getSharedPreferences("Data", Context.MODE_PRIVATE)
                    var value  = pref.getString("listdata","")
                    var data = Gson().fromJson(value,DataFetch::class.java)
                    mutablePostList.postValue(data.data)
                }catch(e: Exception){

                }

            }
        })


        return mutablePostList


    }

}