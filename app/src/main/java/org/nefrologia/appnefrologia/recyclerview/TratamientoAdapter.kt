package org.nefrologia.appnefrologia.recyclerview

import android.view.ViewGroup
import android.widget.Filter
import org.nefrologia.appnefrologia.client.Model
import org.nefrologia.appnefrologia.tools.ModelAdapter

class TratamientoAdapter(
    private val items: MutableList<Model.TratamientoResponse>,
    private val listener: ClickListener<Model.TratamientoResponse>
) : ModelAdapter<TratamientoViewHolder, Model.TratamientoResponse>(items, listener) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TratamientoViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}