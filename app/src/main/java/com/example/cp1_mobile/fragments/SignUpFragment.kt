package com.example.cp1_mobile.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cp1_mobile.R
import com.example.cp1_mobile.databinding.FragmentSignUpBinding
import com.example.cp1_mobile.model.Medicine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        datePicker()
        binding.backToLoginButton.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.buttonSingup.setOnClickListener {
            val name = binding.insertName.text.toString().trim()
            val lastName = binding.insertLastname.text.toString().trim()
            val email = binding.insertEmail.text.toString().trim()
            val dayOfBirth = binding.insertDayOfBirth.text.toString().trim()
            val password = binding.insertPassword.text.toString().trim()

            if (name.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && dayOfBirth.isNotEmpty() && password.isNotEmpty()) {
                if (isValidEmail(email)) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Cadastro bem-sucedido!", Toast.LENGTH_SHORT)
                                .show()

                            val userEmail = auth.currentUser?.email?.replace(".", "_")
                            if (userEmail != null) {
                                database.child("users").child(userEmail).child("name").setValue(name)
                                database.child("users").child(userEmail).child("lastName").setValue(lastName)
                                database.child("users").child(userEmail).child("dayOfBirth").setValue(dayOfBirth)
                            }

                            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
                        } else {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Email inválido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cuidadoplus-97add-default-rtdb.firebaseio.com/")
    }

    private fun datePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate(calendar)
        }

        binding.insertDayOfBirth.setOnClickListener {
            DatePickerDialog(requireContext(), datePicker, calendar.get(Calendar.YEAR), calendar.get(
                Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateDate(calendar: Calendar) {
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.ROOT)
        binding.insertDayOfBirth.setText(sdf.format(calendar.time))
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        return email.matches(emailRegex.toRegex())
    }

}