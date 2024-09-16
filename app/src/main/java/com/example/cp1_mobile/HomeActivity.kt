package com.example.cp1_mobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    private var title: TextView? = null
    private var emergencyButton: Button? = null
    private var homeNav: ImageButton? = null
    private var medNav: ImageButton? = null
    private var userNav: ImageButton? = null

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
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navigationMenu()

        val userName = intent.getStringExtra("name")

        title = findViewById(R.id.title)
        title?.text = "Olá, $userName"

        emergencyButton = findViewById(R.id.emergencyButton)
        emergencyButton?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:192")
            startActivity(intent)
        }

    }
}