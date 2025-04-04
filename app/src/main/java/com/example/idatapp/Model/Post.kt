package com.example.idatapp.Model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId") val IDUsuario: Int,
    @SerializedName("id") val ID: Int,
    @SerializedName("title") val Titulo: String,
    @SerializedName("body") val Contenido: String
)
