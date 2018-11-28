package org.nefrologia.appnefrologia.customview

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.card_menu_item.view.*
import org.nefrologia.appnefrologia.R

class MenuItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.card_menu_item, this, false)
        this.addView(view)
    }

    fun setData(title: String, information: String, drawableResource: Int) {
        this.mnu_title.text = title
        this.mnu_information.text = information
        this.mnu_image.setBackgroundResource(drawableResource)
    }
}