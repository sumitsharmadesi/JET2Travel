package com.sumit.jet2travel.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumit.jet2travel.DataModel
import com.sumit.jet2travel.databinding.DataModelBinding
import java.util.ArrayList

class CategoryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mCategoryList = ArrayList<DataModel>()

    fun setAppList(categoryModel: List<DataModel>) {
        mCategoryList.addAll(categoryModel)
        //notifyItemRangeInserted(0, categoryModel.size)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("LIST_SIZE","" + mCategoryList.size)
        return mCategoryList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val appInfo = mCategoryList[position]
        (holder as CategoryAdapter.RecyclerHolderCatIcon).bind(appInfo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val applicationBinding = DataModelBinding.inflate(layoutInflater, parent, false)
        return RecyclerHolderCatIcon(applicationBinding)
    }




    inner class RecyclerHolderCatIcon(private var applicationBinding: DataModelBinding) : RecyclerView.ViewHolder(applicationBinding.root) {

        fun bind(appInfo: DataModel) {
            applicationBinding.appModelData  = appInfo

        }


    }
}