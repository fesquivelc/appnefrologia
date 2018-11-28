package org.nefrologia.appnefrologia.tools

import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView

/**
 * Created by fesqu on 28/04/2018.
 */

abstract class ModelAdapter<T : ModelViewHolder<S>, S>(
    private val items: MutableList<S>,
    private val listener: ClickListener<S>
) : RecyclerView.Adapter<T>() {

    override fun onBindViewHolder(@NonNull holder: T, position: Int) {
        val item = items[position]

        // Populate view
        holder.populate(item)

        holder.holder.setOnClickListener { listener.onClick(item, items.indexOf(item))}
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ClickListener<S> {
        fun onClick(item: S, position: Int)
        fun onChangeSelection(haveSelected: Boolean)
        fun onCountSelection(count: Int)
    }

    fun addItem(item: S) {
        items.add(item)
        notifyDataSetChanged()
    }


}