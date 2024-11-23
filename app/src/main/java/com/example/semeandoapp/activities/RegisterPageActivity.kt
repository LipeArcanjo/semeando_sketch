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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

class RegisterPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializa o Firebase Auth
        auth = FirebaseAuth.getInstance()


        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginRedirectText = findViewById<TextView>(R.id.loginRedirectText)


        // Redirecionar para a página de login
        loginRedirectText.setOnClickListener {
            val intent = Intent(this, LoginPageActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInputs( email, password)) {
                registerUser( email, password)
            }
        }
    }

    private fun validateInputs( email: String, password: String): Boolean {

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

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    showToast("Usuário registrado com sucesso!")
                    navigateToLoginPage()
                } else {
                    showToast("Erro ao registrar usuário. Tente novamente.")
                }
            }
    }

    private fun navigateToLoginPage() {
        val intent = Intent(this, LoginPageActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
