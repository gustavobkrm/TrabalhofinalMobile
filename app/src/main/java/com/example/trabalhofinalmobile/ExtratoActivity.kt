package com.example.trabalhofinalmobile

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExtratoActivity : AppCompatActivity() {
    private var listCadastroInfo : ArrayList<CadastroInfo>? = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_extrato)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle = intent.extras

        val cadastroInfo = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelableArrayList("listCadastroInfo", CadastroInfo::class.java)
        } else{
            bundle?.getParcelableArrayList("listCadastroInfo")
        }
        println("aqui no extrato")
        println(cadastroInfo)

        listCadastroInfo = cadastroInfo
        val listView = findViewById<ListView>(R.id.listView)
        val arrayAdapter = listCadastroInfo?.let {
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                it.map { it -> it.descricao }
            )
        }
        listView.adapter =  arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val tipoPagamento = listCadastroInfo?.get(position)?.tipoPagamento

            val tipoPagamentoDescricao = when(tipoPagamento) {
                'C' -> "Crédito"
                'D' -> "Débito"
                else -> ""
            }
            Toast.makeText(this, "Valor: R$" + String.format("%.2f",listCadastroInfo?.get(position)?.valor) + " " + "Tipo: " + tipoPagamentoDescricao ,Toast.LENGTH_LONG).show() }
    }
}