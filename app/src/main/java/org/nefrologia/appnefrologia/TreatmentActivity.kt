package org.nefrologia.appnefrologia

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_treatment.*
import kotlinx.android.synthetic.main.content_treatment.*
import org.nefrologia.appnefrologia.client.ApiService
import org.nefrologia.appnefrologia.client.Model
import org.nefrologia.appnefrologia.client.SessionService

class TreatmentActivity : AppCompatActivity() {

    val apiService by lazy {
        ApiService.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tratamientos"

        btn_search.setOnClickListener { view -> btnSearchOnClickListener(view) }
    }

    private fun btnSearchOnClickListener(view: View) {
        val monthVal = sp_month.selectedItem!!.toString().split("-")
        val month = monthVal[1].trim().toInt()
        val year = sp_year.selectedItem!!.toString().toInt()

        searchTreatment(month, year)
    }

    private fun searchTreatment(month: Int, year: Int) {
        val pacienteId = SessionService.user!!.pacienteId!!
        val request = Model.TratamientoRequest(pacienteId, month, year)

        disposable = apiService
            .tratamientoList(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("AnalisisHistory", "Resultado recibido $result")
                    result.datos?.let {
                        showInfo(it)
                    }
                    if (result == null) {
                        Snackbar
                            .make(layout_treatment, "No se encontraron datos", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                },
                { error -> Log.d("TreatmentActivity","error: $error")}
            )
    }

    private fun showInfo(response: Model.TratamientoResponse) {
        date.text = response.fecha
        diagnosis.text = response.diagnostico
        treatment.text = response.trato

    }


}
