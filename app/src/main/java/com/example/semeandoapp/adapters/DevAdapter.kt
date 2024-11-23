package com.example.semeandoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semeandoapp.R
import com.example.semeandoapp.models.Dev

class DevAdapter(
    private val devList: List<Dev>,
    private val onMenuClick: (View, Dev) -> Unit
) : RecyclerView.Adapter<DevAdapter.DevViewHolder>() {

    class DevViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.devName)
        val rm: TextView = itemView.findViewById(R.id.devRM)
        val city: TextView = itemView.findViewById(R.id.devCity)
        val institution: TextView = itemView.findViewById(R.id.devInstitution)
        val github: TextView = itemView.findViewById(R.id.devGithub)
        val image: ImageView = itemView.findViewById(R.id.devImage)
        val menuButton: ImageButton = itemView.findViewById(R.id.menuButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dev, parent, false)
        return DevViewHolder(view)
    }

    override fun onBindViewHolder(holder: DevViewHolder, position: Int) {
        val dev = devList[position]
        holder.name.text = dev.name
        holder.rm.text = dev.rm
        holder.city.text = dev.city
        holder.institution.text = dev.institution
        holder.github.text = dev.github
        // Assumindo que as imagens estão no drawable
        val resId = holder.itemView.context.resources.getIdentifier(dev.imageResId, "drawable", holder.itemView.context.packageName)
        holder.image.setImageResource(resId)

        holder.menuButton.setOnClickListener {
            onMenuClick(it, dev)
        }
    }

    override fun getItemCount(): Int = devList.size
}
