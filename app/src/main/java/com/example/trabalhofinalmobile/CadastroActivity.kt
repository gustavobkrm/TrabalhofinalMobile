package com.example.trabalhofinalmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {
    private var listCadastroInfo : ArrayList<CadastroInfo>? = arrayListOf()

    private lateinit var descricaoInput: EditText
    private lateinit var valorInput: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonCredito: RadioButton
    private lateinit var radioButtonDebito: RadioButton
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        descricaoInput = findViewById(R.id.descricaoInput)
        valorInput = findViewById(R.id.valorInput)
        radioGroup = findViewById(R.id.radioGroup)
        radioButtonCredito = findViewById(R.id.radioButtonCredito)
        radioButtonDebito = findViewById(R.id.radioButtonDebito)
        buttonSave = findViewById(R.id.buttonSave)
    }
    fun save(view : View){
        var tipoPagamento = when (radioGroup.checkedRadioButtonId) {
            R.id.radioButtonCredito -> 'C'
            R.id.radioButtonDebito -> 'D'
            else -> ' '
        }

        val cadastroInfo = CadastroInfo(
            descricao = descricaoInput.text.toString(),
            valor = valorInput.text.toString().toDouble(),
            tipoPagamento = tipoPagamento
        )

        listCadastroInfo?.add(cadastroInfo)
        println(listCadastroInfo)
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()

        descricaoInput.setText("")
        valorInput.setText("")
        radioGroup.clearCheck()
    }

    fun close(view:  View){
        val intent = Intent(this, MainActivity::class.java)
        intent.putParcelableArrayListExtra("listCadastroInfo", listCadastroInfo )
        startActivity(intent)
    }
}