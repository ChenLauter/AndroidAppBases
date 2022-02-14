package com.lauter.androidappbases.base.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDBAdapter<DB: ViewDataBinding,T>(private val context: Context?,
                                                    private val layoutRes: Int) : RecyclerView.Adapter<BaseDBAdapter.DBViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBViewHolder {
        val binding = DataBindingUtil.inflate<DB>(
            LayoutInflater.from(context),
            layoutRes,
            parent,
            false
        )
        return DBViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        DataBindingUtil.getBinding<DB>(holder.itemView)?.let {
            onBindViewHolder(it, position)
            it.executePendingBindings()
        }
    }

    var data: List<T> = arrayListOf()
    override fun getItemCount(): Int = data.size

    abstract fun onBindViewHolder(db: DB, position: Int)

    class DBViewHolder(root: View) : RecyclerView.ViewHolder(root)

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<T>) {
        this.data = data
        notifyDataSetChanged()
    }
}