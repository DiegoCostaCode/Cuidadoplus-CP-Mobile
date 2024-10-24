package com.example.cp1_mobile

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cp1_mobile.model.userSingUp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignUpActivity : AppCompatActivity() {

    //classes
    private var editTextName: EditText? = null
    private var editTextLastName: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextDayOfBirth: EditText? = null
    private var editTextPassword: EditText? = null
    private var signUpButton: Button? = null
    private var backToLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextName = findViewById(R.id.insertName)
        editTextLastName = findViewById(R.id.insertLastname)
        editTextEmail = findViewById(R.id.insertEmail)
        editTextDayOfBirth = findViewById(R.id.insertDayOfBirth)
        editTextPassword = findViewById(R.id.insertPassword)
        signUpButton = findViewById(R.id.buttonSingup)
        backToLogin = findViewById(R.id.backToLoginButton)

        backToLogin?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate(calendar)
        }

        // Abrir calendário
        editTextDayOfBirth?.setOnClickListener {
            DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR), calendar.get(
                Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Coleta os valores dos campos
        var name = editTextName.toString()
        var lastName = editTextLastName.toString()
        var email = editTextEmail.toString()
        var password = editTextPassword.toString()
        var dayOfBirth = editTextDayOfBirth.toString()

        //Setando os inputs na classe
        signUpButton?.setOnClickListener {
            val name = editTextName?.text.toString()
            val lastName = editTextLastName?.text.toString()
            val email = editTextEmail?.text.toString()
            val dayOfBirth = editTextDayOfBirth?.text.toString()
            val password = editTextPassword?.text.toString()

            // Verifica se todos os campos estão preenchidos
            if (name.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && dayOfBirth.isNotEmpty() && password.isNotEmpty()) {
                if (isValidEmail(email)) {
                    // Cria o objeto usuário
                    val user = userSingUp(
                        name = name,
                        lastName = lastName,
                        email = email,
                        dayOfBirth = dayOfBirth,
                        password = password
                    )

                    // Exibe o toast de sucesso referenciando o nome do usuário
                    Toast.makeText(
                        this,
                        "Cadastro bem-sucedido!",
                        Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Exibe o toast de erro
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

        private fun updateDate(calendar: Calendar) {
            val format = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(format, Locale.ROOT)
            editTextDayOfBirth?.setText(sdf.format(calendar.time))
        }

        private fun isValidEmail(email: String): Boolean {
            // Regex para validação de email
            val emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
            return email.matches(emailRegex.toRegex())
        }
}