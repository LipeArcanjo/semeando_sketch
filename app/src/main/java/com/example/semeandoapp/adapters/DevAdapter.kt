package com.example.semeandoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semeandoapp.R
import com.example.semeandoapp.models.Dev

// Adaptador para o RecyclerView, responsável por iterar a lista de desenvolvedores
class DevAdapter(private val devsList: List<Dev>) : RecyclerView.Adapter<DevAdapter.DevViewHolder>() {

    // Cria uma nova ViewHolder quando for necessário
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dev, parent, false)
        return DevViewHolder(view)
    }

    // Função para vincular os dados do nosso querido dev a uma posição específica na lista
    override fun onBindViewHolder(holder: DevViewHolder, position: Int) {
        val dev = devsList[position]
        holder.bind(dev)
    }

    // Retorna o número total de itens na lista
    override fun getItemCount(): Int = devsList.size

    class DevViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val devImage: ImageView = itemView.findViewById(R.id.devImage)
        private val devName: TextView = itemView.findViewById(R.id.devName)
        private val devRM: TextView = itemView.findViewById(R.id.devRM)
        private val devCity: TextView = itemView.findViewById(R.id.devCity)
        private val devInstitution: TextView = itemView.findViewById(R.id.devInstitution)
        private val devGithub: TextView = itemView.findViewById(R.id.devGithub)

        // Método que vai vincular os dados do objeto Dev (na models) ao front-end
        fun bind(dev: Dev) {
            devImage.setImageResource(dev.imageResId)
            devName.text = dev.name
            devRM.text = dev.rm
            devCity.text = dev.city
            devInstitution.text = dev.institution
            devGithub.text = dev.github
        }
    }
}
