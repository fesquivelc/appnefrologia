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
import kotlinx.android.synthetic.main.activity_analisis_history.*
import org.nefrologia.appnefrologia.client.ApiService
import org.nefrologia.appnefrologia.client.Model
import org.nefrologia.appnefrologia.client.SessionService

class AnalisisHistoryActivity : AppCompatActivity() {

    val apiService by lazy {
        ApiService.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analisis_history)

        btn_search.setOnClickListener { view -> btnSearchOnClickListener(view) }
    }

    private fun btnSearchOnClickListener(view: View) {
        val monthVal = sp_month.selectedItem!!.toString().split("-")
        val month = monthVal[1].trim().toInt()
        val year = sp_year.selectedItem!!.toString().toInt()

        searchAnalisis(month, year)
    }

    private fun searchAnalisis(month: Int, year: Int) {
        val pacienteId = SessionService.user!!.pacienteId!!
        val request = Model.HistoryRequest(pacienteId, month, year)

        val gson = Gson()
        val requestStr = gson.toJson(request)

        disposable = apiService
            .analisisHistory(request)
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
                            .make(layout_analisis_list, "No se encontraron datos", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                },
                { error -> showError(error) }
            )
    }

    private fun showInfo(response: Model.HistoryResponse) {
        achvc.text = response.achvc
        albumina.text = response.albumina
        calcio.text = response.calcio
        cloro.text = response.cloro
        coretotal.text = response.coretotal
        creatinapre.text = response.creatinapre
        ferritina.text = response.ferritina
        fosfatasa.text = response.fosfatasa
        ureapost.text = response.ureapost
        ureapre.text = response.ureapre
        sodio.text = response.sodio
        hbsag.text = response.hbsag
        tgo.text = response.tgo
        tgp.text = response.tgp
        hiv.text = response.hiv
        potasio.text = response.potasio
        fosforo.text = response.fosforo
        hto.text = response.hto
        saturacion.text = response.saturacion
        hepatitisb.text = response.hepatitisb
        globulina.text = response.globulina
        proteinas.text = response.proteinas
        paratohormona.text = response.paratohormona
        vdrl.text = response.vdrl
        hb.text = response.hb
        hierroserico.text = response.hierroserico
    }

    private fun showError(error: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}
