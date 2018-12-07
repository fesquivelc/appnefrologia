package org.nefrologia.appnefrologia

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_analisis_history.*
import kotlinx.android.synthetic.main.activity_treatment.*
import org.nefrologia.appnefrologia.client.ApiService

class TreatmentActivity : AppCompatActivity() {

    val apiService by lazy {
        ApiService.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        btn_search.setOnClickListener { view -> btnSearchOnClickListener(view) }
    }

    private fun btnSearchOnClickListener(view: View) {

    }




}
