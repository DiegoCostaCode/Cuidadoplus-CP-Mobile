package com.example.cp1_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //classes

    private var editTextNome: EditText? = null
    private var editTextSobrenome: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextDataNascimento:  Button? = null
    private var editTextPassword:  Button? = null

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
        /*
        editTextDataNascimento = findViewById(R.id.insertPassword)
        editTextPassword = findViewById(R.id.insertPassword)

            // Coleta os valores dos campos
            val nome = editTextNome.toString()
            val lastName = editTextSobrenome.toString()
            val sobrenome = editTextSobrenome.toString()
            val email = editTextEmail.toString()
            val password = editTextPassword.toString()
            val dataNascimento = editTextDataNascimento.toString()

            // Formato da data
            val formatoData = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

            val dataNascimentoDate = formatoData.parse(dataNascimento)

            // Cria a instância da classe CadastroUsuario
            val usuario = userSingUp(
                name = nome,
                lastName = sobrenome,
                email = email,
                dataNascimento = dataNascimentoDate,
                password = password
            )

            // Exemplo de uso
            Toast.makeText(this, "Usuário criado: ${usuario.name}", Toast.LENGTH_SHORT).show()

            Inicialize os campos de entrada e o botão
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
                }*/

        }
    }

    /*private fun validateCredentials(username: String, password: String): Boolean {
        return userCredentials[username] == password
    }*/
