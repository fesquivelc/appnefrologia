package org.nefrologia.appnefrologia.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import org.nefrologia.appnefrologia.R
import org.nefrologia.appnefrologia.client.Model
import org.nefrologia.appnefrologia.tools.ModelAdapter


class DoctorViewAdapter(
    private val items: MutableList<Model.Medico>,
    private val listener: ClickListener<Model.Medico>
) : ModelAdapter<DoctorViewHolder, Model.Medico>(items, listener) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DoctorViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemView = layoutInflater.inflate(R.layout.card_doctor_item, viewGroup, false)

        return DoctorViewHolder(itemView)
    }

}
