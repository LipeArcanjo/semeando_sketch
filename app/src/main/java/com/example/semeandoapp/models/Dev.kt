package com.example.semeandoapp.models

// Realizando a tipagem dos dados que vamos receber
data class Dev(
    val rm: String,
    val name: String,
    val city: String,
    val institution: String,
    val github: String,
    val imageResId: Int // Identificador do recurso de imagem no drawable
)
