package org.nefrologia.appnefrologia.recyclerview

import android.view.View
import kotlinx.android.synthetic.main.card_doctor_item.view.*
import org.nefrologia.appnefrologia.R
import org.nefrologia.appnefrologia.client.Model
import org.nefrologia.appnefrologia.tools.ModelViewHolder

class DoctorViewHolder(itemView: View) :
    ModelViewHolder<Model.Medico>(itemView, R.id.card_doctor_item) {
    override fun populate(item: Model.Medico) {
        itemView.doctor_name.text = item.nombres
        itemView.doctor_dni.text = item.dni
        itemView.doctor_email.text = item.email
        itemView.doctor_phone.text = item.telefono
    }

}