package com.ifgoiano.teste005

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ifgoiano.teste005.databinding.ActivityMainBinding
import com.ifgoiano.teste005.databinding.ActivityNameBinding
import com.ifgoiano.teste005.db.DatabaseHandler
import com.ifgoiano.teste005.model.Pessoa

class NameActivity : AppCompatActivity() {
    // Database
    val databaseHandler = DatabaseHandler(this)

    private lateinit var binding: ActivityNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edit = intent.getBooleanExtra("edit", false)
        val position = intent.getIntExtra("position", 0)
        if(edit){
            val pessoa = databaseHandler.getPessoa(position)
            binding.etNome.setText(pessoa.nome)
            binding.btnInsertNome.setText("Editar")
        }
        binding.btnInsertNome.setOnClickListener {
            if(binding.etNome.text.toString() == ""){
                Toast.makeText(this,"Nome está vazio.",Toast.LENGTH_SHORT).show()
            }
            else {
                if(edit){
                    val pessoa = Pessoa(position, binding.etNome.text.toString())
                    databaseHandler.updatePessoa(pessoa)
                    finish()
                }
                else {
                    val pessoa = Pessoa(0, binding.etNome.text.toString())
                    databaseHandler.addPessoa(pessoa)
                    finish()
                }
            }
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}