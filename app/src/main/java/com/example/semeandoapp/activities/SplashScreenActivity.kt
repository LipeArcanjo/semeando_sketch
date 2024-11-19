package com.example.semeandoapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.semeandoapp.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Inicializando o botão
        val buttonStart: Button = findViewById(R.id.buttonStart)

        // Configurando clique do botão para redirecionar à tela de login
        buttonStart.setOnClickListener {
            val intent = Intent(this, LoginPageActivity::class.java)
            startActivity(intent)
            finish() // Finaliza a splash screen para que o usuário não possa voltar a ela.
        }
}}