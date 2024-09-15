package com.example.cp1_mobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cp1_mobile.Model.Medicine

class HomeActivity : AppCompatActivity() {

    private var helloUserText: TextView? = null
    private var emergencyButton: Button? = null
    private lateinit var medicinesRecyclerView: RecyclerView
    private lateinit var addMedicineButton: Button
    private val medicines = mutableListOf<Medicine>()
    private lateinit var medicineAdapter: MedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userName = intent.getStringExtra("name")

        helloUserText = findViewById(R.id.helloUser)
        helloUserText?.text = "Olá, $userName"

        emergencyButton = findViewById(R.id.emergencyButton)
        emergencyButton?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:192")
            startActivity(intent)
        }

        medicinesRecyclerView = findViewById(R.id.medicinesRecyclerView)
        medicinesRecyclerView.layoutManager = LinearLayoutManager(this)
        medicineAdapter = MedicineAdapter(medicines)
        medicinesRecyclerView.adapter = medicineAdapter

        addMedicineButton = findViewById(R.id.addMedicineButton)
        addMedicineButton.setOnClickListener {
            val newMedicine = Medicine("Paracetamol", "08:00 AM")
            medicines.add(newMedicine)
            medicineAdapter.notifyDataSetChanged()
        }
    }
}