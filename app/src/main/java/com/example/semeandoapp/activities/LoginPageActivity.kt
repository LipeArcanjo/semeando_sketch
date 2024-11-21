package com.example.semeandoapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.semeandoapp.R

class LoginPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailField = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordField = findViewById<EditText>(R.id.editTextTextPassword)
        val loginButton = findViewById<Button>(R.id.button)
        val registerLink = findViewById<TextView>(R.id.registerLink)

        // Dados de exemplo para login (substitua por autenticação real)
        val correctEmail = "admin@email.com"
        val correctPassword = "123456"

        // Ação do botão de login
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else if (email == correctEmail && password == correctPassword) {
                // Login bem-sucedido
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                // Exemplo de redirecionamento para uma tela principal
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Falha no login
                Toast.makeText(this, "Credenciais inválidas. Tente novamente!", Toast.LENGTH_SHORT).show()
            }
        }

        // Link para a página de registro
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterPageActivity::class.java)
            startActivity(intent)
        }



    }
}
