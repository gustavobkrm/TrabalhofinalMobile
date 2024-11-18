package com.example.trabalhofinalmobile

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var listCadastroInfo : ArrayList<CadastroInfo>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonClose = findViewById<Button>(R.id.buttonClose)
        buttonClose.setOnClickListener(){
            finishAffinity()
        }

        val bundle = intent.extras

        val cadastroInfo = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelableArrayList("listCadastroInfo", CadastroInfo::class.java)
        } else{
            bundle?.getParcelableArrayList("listCadastroInfo")
        }

        listCadastroInfo = cadastroInfo
        println("aqui na main")
        println(listCadastroInfo)
    }

        fun goCadastro(view : View){
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
        fun goExtrato(view : View){
            println("aqui no metodo extrato")
            println(listCadastroInfo)
            val intent = Intent(this, ExtratoActivity::class.java)
            intent.putParcelableArrayListExtra("listCadastroInfo", listCadastroInfo )
            startActivity(intent)
        }
}