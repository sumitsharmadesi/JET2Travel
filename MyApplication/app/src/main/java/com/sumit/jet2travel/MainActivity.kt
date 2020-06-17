package com.sumit.jet2travel

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sumit.jet2travel.Model.DataFetch
import com.sumit.jet2travel.adapters.CategoryAdapter
import com.sumit.jet2travel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {



    private lateinit var appStoreHomeViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    var dataList: List<DataModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        appStoreHomeViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.mainModelHome = appStoreHomeViewModel

        setRecyclerView(dataList)   //send empty list initially

        subscribeDataCallBack()


    }


    private fun subscribeDataCallBack() {

                appStoreHomeViewModel.getProjectList()?.observe(this, Observer<List<DataModel>> {

            if (it != null) {
                var fe = DataFetch()
                fe.data = it
                try {
                    val pref: SharedPreferences = this.getSharedPreferences("Data", Context.MODE_PRIVATE)
                    val editor = pref.edit()
                    var data = Gson().toJson(fe)
                    editor.putString("listdata",data)
                    editor.commit()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                categoryAdapter.setAppList(it)
            }

        })

    }

    private lateinit var categoryAdapter: CategoryAdapter

    private fun setRecyclerView(dataList: List<DataModel>) {
        categoryAdapter = CategoryAdapter()
        val categoryLinearLayoutManager = LinearLayoutManager(this)
        categoryLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.recycler.layoutManager = categoryLinearLayoutManager
        categoryAdapter.setAppList(dataList)
        binding.root.recycler.adapter = categoryAdapter


    }
}
