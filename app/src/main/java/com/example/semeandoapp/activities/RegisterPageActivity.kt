package com.example.semeandoapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.semeandoapp.R
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

class RegisterPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginRedirectText = findViewById<TextView>(R.id.loginRedirectText)

        // Configuração do botão de registro
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                val result = registerUser(username, email, password)
                if (result) {
                    Toast.makeText(this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginPageActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao registrar. Tente novamente!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Redirecionar para a página de login
        loginRedirectText.setOnClickListener {
            val intent = Intent(this, LoginPageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(username: String, email: String, password: String): Boolean {
        var connection: Connection? = null
        var preparedStatement: PreparedStatement? = null
        return try {
            // Conexão com o banco de dados Oracle
            val url = "jdbc:oracle:thin:@<HOST>:<PORT>:<SERVICE>"
            val user = "<SEU_USUARIO>"
            val pass = "<SUA_SENHA>"

            connection = DriverManager.getConnection(url, user, pass)

            val query = "INSERT INTO usuarios (nome_usuario, email, senha) VALUES (?, ?, ?)"
            preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, username)
            preparedStatement.setString(2, email)
            preparedStatement.setString(3, password)

            preparedStatement.executeUpdate() > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            try {
                preparedStatement?.close()
                connection?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
