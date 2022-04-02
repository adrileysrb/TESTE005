package com.ifgoiano.teste005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifgoiano.teste005.databinding.ActivityMainBinding
import com.ifgoiano.teste005.db.DatabaseHandler
import com.ifgoiano.teste005.model.Pessoa

class MainActivity : AppCompatActivity() {
    // Iniciando a RecyclerView
    var listaAdapter: ListaAdapter? = null
    var linearLayoutManager: LinearLayoutManager? = null

    // SQLite
    var pessoaList = ArrayList<Pessoa>()
    var databaseHandler = DatabaseHandler(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()

        binding.btnInsert.setOnClickListener {
            val intent = Intent(this, NameActivity::class.java)
            startActivityForResult(intent,1)
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView(){
        pessoaList = databaseHandler.pessoas()
        listaAdapter = ListaAdapter(pessoaList,this, this::deleteAdapter)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = listaAdapter
    }
    private fun deleteAdapter(position: Int){
        pessoaList.removeAt(position)
        listaAdapter!!.notifyItemRemoved(position)
    }
}