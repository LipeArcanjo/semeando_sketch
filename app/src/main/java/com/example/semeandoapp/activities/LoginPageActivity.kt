package com.example.semeandoapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.semeandoapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth // Instância do FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializa o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Referências aos componentes do layout
        val emailField = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordField = findViewById<EditText>(R.id.editTextTextPassword)
        val loginButton = findViewById<Button>(R.id.button)
        val registerLink = findViewById<TextView>(R.id.registerLink)

        // Ação para login
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (validateInputs(email, password)) {
                loginUser(email, password)
            }
        }

        // Navegação para a tela de registro
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterPageActivity::class.java)
            startActivity(intent)
        }
    }

    // Validação dos campos de e-mail e senha
    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            showToast("Por favor, insira um e-mail.")
            return false
        }
        if (password.isEmpty()) {
            showToast("Por favor, insira uma senha.")
            return false
        }
        if (password.length < 6) {
            showToast("A senha deve ter no mínimo 6 caracteres.")
            return false
        }
        return true
    }

    // Função para realizar login no Firebase
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido
                    showToast("Login realizado com sucesso!")
                    navigateToMainPage()
                } else {
                    // Falha no login
                    showToast("Erro ao realizar login: ${task.exception?.message}")
                }
            }
    }

    // Função para navegar para tela principal após login ser bem-sucedido
    private fun navigateToMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    // Criação de uma função para exibição das mensagens de erro
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
