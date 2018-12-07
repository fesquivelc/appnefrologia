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
        val token: String? = null,
        @SerializedName("paciente_id") val pacienteId: Int? = null
    )

    data class HistoryRequest(
        @SerializedName("paciente_id") val pacienteId: Int,
        val mes: Int,
        val anio: Int
    )

    data class HistoryResponse(
        val anio: String? = null,

        val paciente: String? = null,

        val hbsag: String? = null,

        val tgo: String? = null,

        val fosfatasa: String? = null,

        val tgp: String? = null,
        val coretotal: String? = null,

        val sodio: String? = null,

        val id: String? = null,

        @SerializedName("id_paciente") val idPaciente: String? = null,

        val hiv: String? = null,

        val cloro: String? = null,

        val mes: String? = null,

        val potasio: String? = null,

        val fosforo: String? = null,

        val hto: String? = null,

        val saturacion: String? = null,

        val hepatitisb: String? = null,

        val albumina: String? = null,

        val globulina: String? = null,

        val fecha: String? = null,

        val proteinas: String? = null,

        val paratohormona: String? = null,

        val creatinapre: String? = null,

        val calcio: String? = null,

        val code: String? = null,

        val achvc: String? = null,

        val vdrl: String? = null,

        val hb: String? = null,
        val hierroserico: String? = null,

        val ferritina: String? = null,

        val ureapre: String? = null,

        val ureapost: String? = null
    )

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