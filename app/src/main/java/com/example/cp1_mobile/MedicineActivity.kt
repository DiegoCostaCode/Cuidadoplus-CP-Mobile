package com.example.cp1_mobile

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import java.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cp1_mobile.Model.Medicine
import java.text.SimpleDateFormat
import java.util.Locale

class MedicineActivity : AppCompatActivity() {

    private var homeNav: ImageButton? = null
    private var medNav: ImageButton? = null
    private var userNav: ImageButton? = null
    private var addMedicineButton: Button? = null
    private var medicines = mutableListOf<Medicine>()
    private var medicineTimeEditText: EditText? = null
    private var medicineNameEditText: EditText? = null

    fun navigationMenu() {
        homeNav = findViewById(R.id.homeNav)
        medNav = findViewById(R.id.medNav)
        userNav = findViewById(R.id.userNav)

        homeNav?.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        medNav?.setOnClickListener {
            startActivity(Intent(this, MedicineActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_medicine)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navigationMenu()

        val medicinesList = findViewById<LinearLayout>(R.id.medicinesList)

        fun updateMedicineList() {
            medicinesList.removeAllViews()
            medicines.forEach { medicine ->
                val textView = TextView(this)
                textView.text = "${medicine.name} - ${medicine.time}"
                textView.textSize = 18f
                textView.setTextColor(Color.parseColor("#FF6A00"))
                textView.setPadding(4, 0, 4, 15)
                medicinesList.addView(textView)
            }
        }

        addMedicineButton = findViewById(R.id.addMedicineButton)
        addMedicineButton?.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.add_medicine_dialog, null)

            builder.setView(dialogView)

            medicineNameEditText = dialogView.findViewById(R.id.medicineNameEditText)
            medicineTimeEditText = dialogView.findViewById(R.id.medicineTimeEditText)

            medicineTimeEditText?.setOnClickListener {
                Log.d("aaa", "funcionoa pelo amor de deus")
                val calendar = Calendar.getInstance()
                val timePicker = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    medicineTimeEditText?.setText(SimpleDateFormat("HH:mm").format(calendar.time))
                }
                TimePickerDialog(this, timePicker, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
            }

            builder.setTitle("Adicionar Novo Remédio")

            builder.setPositiveButton("Salvar") { dialog, _ ->
                val medicineName = medicineNameEditText?.text.toString()
                val medicineTime = medicineTimeEditText?.text.toString()

                if (medicineName.isNotEmpty() && medicineTime.isNotEmpty()) {
                    medicines.add(Medicine(medicineName, medicineTime))

                    updateMedicineList()
                } else {
                    Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

}