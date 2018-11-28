package org.nefrologia.appnefrologia.tools

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class ModelViewHolder<T>(itemView: View, root: Int) : RecyclerView.ViewHolder(itemView) {
    var holder: View = itemView.findViewById(root)
    //    private val root: Int? = null
    var selected: View? = null

    //    constructor(itemView: View) : super(itemView) {
//        holder = itemView.findViewById(root!!)
//    }

    abstract fun populate(item: T)
}