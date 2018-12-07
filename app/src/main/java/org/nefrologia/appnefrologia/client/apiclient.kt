package org.nefrologia.appnefrologia.client

import android.util.Log
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL = "http://10.0.2.2/clinica_api/webservice/"
//private const val BASE_URL = "http://192.168.1.176/clinica_api/webservice/"

private const val TAG_ERROR = "ApiService:ERROR"
private const val TAG_INFO = "ApiService:INFO"

interface ApiService {
    companion object {
        private val defaultHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request: Request
                if (SessionService.token.isNullOrEmpty()) {
                    Log.d(TAG_ERROR, "Token existente o no se puede agregar")
                    request = chain.request()
                } else {
                    request = chain.request().newBuilder()
                        .addHeader("token", SessionService.token!!).build()
                    Log.d(TAG_INFO, "Token agregado correctamente")
                }
                return@Interceptor chain.proceed(request)
            }).build()

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(defaultHttpClient)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @POST("uservalidar.php")
    fun login(@Body user: Model.LoginRequest): Observable<Model.ResponseWrapper<Model.LoginResponse>>

    @GET("medicos_list.php")
    fun medicoList(): Observable<Model.ResponseWrapper<List<Model.Medico>>>

    @POST("analisisHistory_paciente.php")
    fun analisisHistory(@Body request: Model.HistoryRequest): Observable<Model.ResponseWrapper<Model.HistoryResponse>>

    @POST("tratamiento_paciente.php")
    fun tratamientoList(@Body request: Model.TratamientoRequest): Observable<Model.ResponseWrapper<Model.TratamientoResponse>>


}