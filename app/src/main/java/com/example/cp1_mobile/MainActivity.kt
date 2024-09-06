package com.example.cp1_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cp1_mobile.Model.userSingUp
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    //classes

    private var editTextNome: EditText? = null
    private var editTextSobrenome: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextDataNascimento: EditText? = null
    private var editTextPassword: EditText? = null
    private var cadastroButton: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextNome = findViewById(R.id.insertName)
        editTextSobrenome = findViewById(R.id.insertLastname)
        editTextEmail = findViewById(R.id.insertEmail)
        editTextDataNascimento = findViewById(R.id.insertNascimentoData)
        editTextPassword = findViewById(R.id.insertPassword)
        cadastroButton = findViewById(R.id.buttonSingup)


        // Coleta os valores dos campos
        var nome = editTextNome.toString()
        var lastName = editTextSobrenome.toString()
        var sobrenome = editTextSobrenome.toString()
        var email = editTextEmail.toString()
        var password = editTextPassword.toString()
        var dataNascimento = editTextDataNascimento.toString()

        //Setando os inputs na classe
        cadastroButton?.setOnClickListener {
            val nome = editTextNome?.text.toString()
            val sobrenome = editTextSobrenome?.text.toString()
            val email = editTextEmail?.text.toString()
            val dataNascimento = editTextDataNascimento?.text.toString()
            val password = editTextPassword?.text.toString()

            // Verifica se todos os campos estão preenchidos
            if (nome.isNotEmpty() && sobrenome.isNotEmpty() && email.isNotEmpty() && dataNascimento.isNotEmpty() && password.isNotEmpty()) {
                // Cria o objeto usuário
                val usuario = userSingUp(
                    name = nome,
                    lastName = sobrenome,
                    email = email,
                    dataNascimento = dataNascimento,
                    password = password
                )

                // Exibe o toast de sucesso referenciando o sobrenome do usuário
                Toast.makeText(
                    this,
                    "Cadastro bem-sucedido! Bem-vindo, $sobrenome!",
                    Toast.LENGTH_SHORT).show()

                val intent = Intent(this, SegundaTela::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                startActivity(intent)
            } else {
                // Exibe o toast de erro
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}


