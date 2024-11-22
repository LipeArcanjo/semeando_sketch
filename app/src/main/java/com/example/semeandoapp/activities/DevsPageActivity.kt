package com.example.semeandoapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semeandoapp.R
import com.example.semeandoapp.adapters.DevAdapter
import com.example.semeandoapp.models.Dev
import com.example.semeandoapp.utils.MenuNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class DevsPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devs)

        // Configuração do BottomNavigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.nav_devs
        MenuNavigation.setupBottomNavigation(this, bottomNavigationView)

        // Dados mockados (Por enquanto hehe)
        val devsList = listOf(
            Dev("RM: 554018", "Felipe Arcanjo", "São Paulo", "FIAP", "Github: LipeArcanjo", R.drawable.lipe_img),
            Dev("RM: 553326", "Gustavo Rabelo", "São Paulo", "FIAP", "Github: rbll0", R.drawable.gus_img),
            Dev("RM: 553640", "Marcelo Vieira", "São Paulo", "FIAP", "Github: marcelo-vj28", R.drawable.celo_img)
        )

        // Configuração para utilizarmos o RecyclerView a fim de fazer um map dos Devs
        val recyclerView = findViewById<RecyclerView>(R.id.devsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DevAdapter(devsList)
    }
}
