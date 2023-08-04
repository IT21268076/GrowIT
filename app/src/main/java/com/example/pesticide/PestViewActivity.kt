package com.example.pesticide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pesticide.databinding.ActivityViewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PestViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchName: String = binding.searchName.text.toString()
            if (searchName.isNotEmpty()) {
                readData(searchName)
            } else {
                Toast.makeText(this, "Please enter pesticide name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.updateButton.setOnClickListener {
            val pesticideName: String = binding.readName.text.toString()
            val price: String = binding.readPrice.text.toString()
            val expDate: String = binding.readExpDate.text.toString()
            val volume: String = binding.readVolume.text.toString()

            val intent = Intent(this, PestUpdateActivity::class.java).apply {
                putExtra("pesticideName", pesticideName)
                putExtra("price", price)
                putExtra("expDate", expDate)
                putExtra("volume", volume)
            }
            startActivity(intent)
        }
    }

    private fun readData(name: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Pesticide Directory")
        databaseReference.child(name).get().addOnSuccessListener {
            if(it.exists()){
                val name = it.child("name").value
                val price = it.child("price").value
                val volume = it.child("volume").value
                val expDate = it.child("expDate").value
                val cropType = it.child("cropType").value

                Toast.makeText(this, "Results found", Toast.LENGTH_SHORT).show()
                binding.searchName.text.clear()
                binding.readName.text = name.toString()
                binding.readPrice.text = price.toString()
                binding.readVolume.text = volume.toString()
                binding.readExpDate.text = expDate.toString()
                binding.readCropType.text = cropType.toString()

            } else {
                Toast.makeText(this, "Name does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
