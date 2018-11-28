package org.nefrologia.appnefrologia.client

import com.google.gson.annotations.SerializedName
import java.util.*

object Model {
    data class LoginRequest(
        @SerializedName("p_dni") val dni: String,
        @SerializedName("p_clave") val clave: String
    )

    data class ResponseWrapper<T>(
        val estado: Int,
        val mensaje: String,
        val datos: T? = null
    )

    data class LoginResponse(
        @SerializedName("tipo_usuario") val tipoUsuario: String? = null,
        @SerializedName("nombre_usuario") val nombreUsuario: String? = null,
        val id: Int? = null,
        val dni: String? = null,
        val clave: String? = null,
        val estado: String? = null,
        val token: String? = null
    )

//    data class LoginResponse(
//        val estado: Int? = null,
//        val mensaje: String? = null,
//        val datos: LoginResponseContent? = null
//    )

    data class Medico(
        var dni: String? = null,
        var me: String? = null,
        var cmp: String? = null,
        var apellidos: String? = null,
        var nombres: String? = null,
        var email: String? = null,
        var telefono: String? = null,
        var estado: String? = null
    )

    data class TratamientoRequest(
        @SerializedName("fecha_inicio") var fechaInicio: Date? = null,
        @SerializedName("fecha_fin") var fechaFin: Date? = null,
        var param: String? = null
    )

    data class TratamientoResponse(
        var paciente: String? = null,
        var fecha: Date? = null,
        var diagnostico: String? = null,
        var trato: String? = null
    )
}