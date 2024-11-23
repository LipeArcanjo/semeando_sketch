package com.example.semeandoapp.models

import com.google.firebase.database.IgnoreExtraProperties

// Realizando a tipagem dos dados que vamos receber
@IgnoreExtraProperties
data class Dev(
    val id: String = "", // ID armazenado como String para compatibilidade com Firebase
    val rm: String = "",
    val name: String = "",
    val city: String = "",
    val institution: String = "",
    val github: String = "",
    val imageResId: String = "", // String para armazenar referências de imagens
    val protected: Boolean = false
)
