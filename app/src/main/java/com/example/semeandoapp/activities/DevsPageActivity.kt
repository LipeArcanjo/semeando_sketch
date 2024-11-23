    package com.example.semeandoapp.activities

    import android.app.AlertDialog
    import android.os.Bundle
    import android.view.View
    import android.widget.Button
    import android.widget.EditText
    import android.widget.PopupMenu
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.semeandoapp.R
    import com.example.semeandoapp.adapters.DevAdapter
    import com.example.semeandoapp.models.Dev
    import com.example.semeandoapp.utils.MenuNavigation
    import com.google.android.material.bottomnavigation.BottomNavigationView
    import com.google.android.material.floatingactionbutton.FloatingActionButton
    import com.google.firebase.database.*
    import com.google.firebase.database.DataSnapshot

    class DevsPageActivity : AppCompatActivity() {

        private lateinit var database: DatabaseReference
        private lateinit var devAdapter: DevAdapter
        private lateinit var devList: MutableList<Dev>
        private lateinit var recyclerView: RecyclerView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_devs)

            // Configuração do BottomNavigation
            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView.selectedItemId = R.id.nav_devs
            MenuNavigation.setupBottomNavigation(this, bottomNavigationView)

            // Inicializar Firebase Database com URL personalizada
            database = FirebaseDatabase.getInstance("https://semeando-mobile-b22f3-default-rtdb.firebaseio.com/").getReference("devs")

            // Configurar RecyclerView
            recyclerView = findViewById(R.id.devsRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)
            devList = mutableListOf()
            devAdapter = DevAdapter(devList, ::showMenu)
            recyclerView.adapter = devAdapter

            // Ler dados do Firebase
            fetchDevsFromFirebase()

            // Botão flutuante para adicionar novos devs
            val addDevFab = findViewById<FloatingActionButton>(R.id.addDevFab)
            addDevFab.setOnClickListener {
                showAddDevDialog()
            }
        }



        // Função para buscar dados no Firebase
        private fun fetchDevsFromFirebase() {
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    devList.clear()
                    for (devSnapshot in snapshot.children) {
                        val dev = devSnapshot.getValue(Dev::class.java)?.copy(id = devSnapshot.key ?: "")
                        if (dev != null) {
                            devList.add(dev)
                        }
                    }
                    devAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@DevsPageActivity, "Erro ao carregar dados: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }


        // Mostrar menu contextual (editar/excluir)
        private fun showMenu(view: View, dev: Dev) {
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.dev_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.editDev -> {
                        if (dev.protected) {
                            Toast.makeText(this, "Este dev não pode ser editado.", Toast.LENGTH_SHORT).show()
                        } else {
                            showEditDevDialog(dev)
                        }
                        true
                    }
                    R.id.deleteDev -> {
                        if (dev.protected) {
                            Toast.makeText(this, "Este dev não pode ser excluído.", Toast.LENGTH_SHORT).show()
                        } else {
                            deleteDevFromFirebase(dev)
                        }
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        // Exibir diálogo para adicionar novo dev
        private fun showAddDevDialog() {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_dev, null)
            val rmInput = dialogView.findViewById<EditText>(R.id.rmInput)
            val nameInput = dialogView.findViewById<EditText>(R.id.nameInput)
            val cityInput = dialogView.findViewById<EditText>(R.id.cityInput)
            val institutionInput = dialogView.findViewById<EditText>(R.id.institutionInput)
            val githubInput = dialogView.findViewById<EditText>(R.id.githubInput)

            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .create()

            val saveButton = dialogView.findViewById<Button>(R.id.saveDevButton)
            saveButton.setOnClickListener {
                val rm = rmInput.text.toString().trim()
                val name = nameInput.text.toString().trim()
                val city = cityInput.text.toString().trim()
                val institution = institutionInput.text.toString().trim()
                val github = githubInput.text.toString().trim()

                if (rm.isNotEmpty() && name.isNotEmpty() && city.isNotEmpty() && institution.isNotEmpty() && github.isNotEmpty()) {
                    val newDev = Dev(rm, name, city, institution, github, "default_image", " ",false)
                    addDevToFirebase(newDev)
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
                }
            }

            dialog.show()
        }

        // Adicionar dev no Firebase
        private fun addDevToFirebase(dev: Dev) {
            database.orderByKey().limitToLast(1).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lastId = if (snapshot.exists()) {
                        snapshot.children.last().key?.toIntOrNull() ?: 0
                    } else {
                        0
                    }
                    val newId = (lastId + 1).toString()

                    // Adicionar dev com ID sequencial
                    val newDev = dev.copy(id = newId)
                    database.child(newId).setValue(newDev).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@DevsPageActivity, "Dev adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@DevsPageActivity, "Erro ao salvar o Dev.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@DevsPageActivity, "Erro ao gerar ID: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }



        // Exibir diálogo para editar dev
        private fun showEditDevDialog(dev: Dev) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_dev, null)
            val rmInput = dialogView.findViewById<EditText>(R.id.rmInput)
            val nameInput = dialogView.findViewById<EditText>(R.id.nameInput)
            val cityInput = dialogView.findViewById<EditText>(R.id.cityInput)
            val institutionInput = dialogView.findViewById<EditText>(R.id.institutionInput)
            val githubInput = dialogView.findViewById<EditText>(R.id.githubInput)
            val saveButton = dialogView.findViewById<Button>(R.id.saveDevButton)

            // Preencher os campos com os dados do dev
            rmInput.setText(dev.rm)
            nameInput.setText(dev.name)
            cityInput.setText(dev.city)
            institutionInput.setText(dev.institution)
            githubInput.setText(dev.github)

            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(true)
                .create()

            saveButton.setOnClickListener {
                val updateDev = dev.copy(
                    rm = rmInput.text.toString(),
                    name = nameInput.text.toString(),
                    city = cityInput.text.toString(),
                    institution = institutionInput.text.toString(),
                    github = githubInput.text.toString(),
                )
                updateDevInFirebase(updateDev)
                dialog.dismiss()
            }

            dialog.show()
        }

        // Atualizar dev no Firebase
        private fun updateDevInFirebase(dev: Dev) {
            database.child(dev.id).setValue(dev).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@DevsPageActivity, "Dev atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DevsPageActivity, "Erro ao atualizar o Dev.", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this@DevsPageActivity, "Erro ao atualizar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }



        // Excluir dev do Firebase
        private fun deleteDevFromFirebase(dev: Dev) {
            database.child(dev.id).removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@DevsPageActivity, "Dev excluído com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DevsPageActivity, "Erro ao excluir o Dev.", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this@DevsPageActivity, "Erro ao excluir: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }
