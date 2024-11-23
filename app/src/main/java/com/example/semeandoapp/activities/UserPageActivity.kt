package com.example.semeandoapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.semeandoapp.R
import com.example.semeandoapp.utils.MenuNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Configuração do BottomNavigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.nav_user
        MenuNavigation.setupBottomNavigation(this, bottomNavigationView)

        // Elementos da página
        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val bioInput = findViewById<EditText>(R.id.bioInput)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        // Função para salvar os dados
        saveButton.setOnClickListener {
            // Exibir mensagem de sucesso
            Toast.makeText(this, "Os dados foram atualizados com sucesso.", Toast.LENGTH_SHORT).show()

            // !IMPORTANT adicionar a lógica para salvar os dados no banco de dados aqui
            val updatedUsername = usernameInput.text.toString()
            val updatedEmail = emailInput.text.toString()
            val updatedBio = bioInput.text.toString()

            // Apenas para depuração
            println("Username: $updatedUsername, Email: $updatedEmail, Bio: $updatedBio")
        }

        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, SplashScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
