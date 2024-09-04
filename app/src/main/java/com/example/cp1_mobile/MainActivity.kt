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

class MainActivity : AppCompatActivity() {
    private var inserteUser: EditText? = null
    private var insertPassword: EditText? = null
    private var loginButton: Button? = null

    //Dicionário para armazenar usuários e senhas
    private var userCredentials: HashMap<String, String> = hashMapOf(
        "user1" to "password1",
        "user2" to "password2",
        // Adicione mais usuários e senhas conforme necessário
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialize os campos de entrada e o botão
        inserteUser = findViewById(R.id.inserteUser)
        insertPassword = findViewById(R.id.insertPassword)
        loginButton = findViewById(R.id.buttonLogin)

        //Exemplo de cadastro
        //O ideal é que seja preenchido por inputs do usuário

        // Configura o ouvinte do botão de login
        loginButton?.setOnClickListener {
            val username = inserteUser?.text.toString()
            val password = insertPassword?.text.toString()

            if (validateCredentials(username, password)) {

                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SegundaTela::class.java)
                startActivity(intent)

            } else {
                userCredentials.put(username,password)
                Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        return userCredentials[username] == password
    }
}
