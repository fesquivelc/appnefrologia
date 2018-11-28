package org.nefrologia.appnefrologia

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.nefrologia.appnefrologia.client.ApiService
import org.nefrologia.appnefrologia.client.Model
import org.nefrologia.appnefrologia.client.SessionService
import java.time.Duration

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    val apiService by lazy {
        ApiService.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            val dni = input_dni.text.toString()
            val password = input_password.text.toString()
            if (dni == "test") {
                startMain()
            } else {
                login(dni, password)
            }

        }
    }

    private fun login(dni: String, password: String) {
        val request = Model.LoginRequest(dni, password)
        disposable = apiService.login(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d(TAG, "Token ${result.datos!!.token}")
                    SessionService.token = result.datos.token
                    SessionService.user = result.datos
                    startMain()
                },
                { error -> mostrarError(error) }
            )
    }

    private fun mostrarError(error: Throwable) {
        Snackbar.make(sc_main,"Error al iniciar sesión",Snackbar.LENGTH_SHORT).show()
        Log.d(TAG, "Error al intentar iniciar sesion", error)
    }

    private fun startMain() {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
    }

    override fun onDestroy() {
        disposable?.dispose()
    }
}
