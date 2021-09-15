package com.lauter.androidappbases.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDataBindingAdapter<VDB: ViewDataBinding,T>(
    private val context: Context?,
    @LayoutRes val layoutId: Int,
    var data: List<T>?=null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =  DataBindingUtil.inflate<VDB>(
            LayoutInflater.from(context),
            layoutId,
            parent,
            false
        )
        return BaseDBViewModel(binding.root)
    }

    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onItemClickListener?.invoke(position) }
        val binding = DataBindingUtil.getBinding<VDB>(holder.itemView)?.apply {
            onBindViewHolder(this,position)
        }
        binding?.executePendingBindings()
    }

    override fun getItemCount(): Int = data?.size?:0

    class BaseDBViewModel(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun submitData(data: List<T>?) {
        this.data = data
        notifyDataSetChanged()
    }

    abstract fun onBindViewHolder(viewDataBinding: VDB,position: Int)

}