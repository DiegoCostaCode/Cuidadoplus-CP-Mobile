package com.example.cp1_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var loginButton: Button? = null
    private var emailLogin: EditText? = null
    private var passwordLogin: EditText? = null
    private var goToSignUp: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userName = intent.getStringExtra("name")
        val userEmail = intent.getStringExtra("email")
        val userPassword = intent.getStringExtra("password")

        emailLogin = findViewById(R.id.loginEmail)
        passwordLogin = findViewById(R.id.loginPassword)
        loginButton = findViewById(R.id.buttonLogin)
        goToSignUp = findViewById(R.id.linkSignUp)

        var email = emailLogin.toString()
        var password = passwordLogin.toString()

        loginButton?.setOnClickListener {
            val email = emailLogin?.text.toString()
            val password = passwordLogin?.text.toString()

            // Verifica se o email e a senha fornecidos correspondem ao cadastro
            if (email == userEmail && password == userPassword) {
                Toast.makeText(this, "Login bem-sucedido! Bem-vindo(a), $userName!", Toast.LENGTH_SHORT).show()
                // Aqui você pode redirecionar para outra tela ou exibir informações
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("name", userName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email ou senha incorretos. Tente novamente.", Toast.LENGTH_SHORT).show()
            }

        }

        goToSignUp?.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}


