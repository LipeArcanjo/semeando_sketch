package com.example.semeandoapp.models

import com.google.firebase.database.IgnoreExtraProperties

// Realizando a tipagem dos dados que vamos receber
@IgnoreExtraProperties
data class Dev(
    val rm: String? = "",
    val name: String? = "",
    val city: String? = "",
    val institution: String? = "",
    val github: String? = "",
    val imageResId: String? = "",
    val protected: Boolean = false
)
