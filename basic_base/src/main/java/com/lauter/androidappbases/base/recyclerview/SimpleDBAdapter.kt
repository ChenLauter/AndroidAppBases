package com.lauter.androidappbases.base.recyclerview

import android.content.Context
import androidx.databinding.ViewDataBinding

class SimpleDBAdapter<DB: ViewDataBinding, T>(context: Context?,
                                              layoutRes: Int,
                                              private val variableId: Int)
    : BaseDBAdapter<DB, T>(context, layoutRes) {

    override fun onBindViewHolder(db: DB, position: Int) {
        db.run {
            setVariable(variableId, data[position])
            onItemClickListener?.let { listener ->
                root.setOnClickListener { listener.invoke(position) }
            }
        }

    }

    var onItemClickListener: ((Int) -> Unit)? = null
}