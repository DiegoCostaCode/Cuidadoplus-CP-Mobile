package com.example.cp1_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cp1_mobile.Model.Medicine

class MedicineAdapter(private val medicines: List<Medicine>) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

    class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.medicineName)
        val timeTextView: TextView = itemView.findViewById(R.id.medicineTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicines[position]
        holder.nameTextView.text = medicine.name
        holder.timeTextView.text = medicine.time
    }

    override fun getItemCount(): Int {
        return medicines.size
    }
}