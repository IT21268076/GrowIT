package com.example.pesticide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pesticide.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PestUploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.saveButton.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val cropType = binding.uploadCropType.text.toString()
            val price = binding.uploadPrice.text.toString()
            val volume = binding.uploadVolume.text.toString()
            val expDate = binding.uploadExpDate.text.toString()

            if (name.isEmpty() || cropType.isEmpty() || price.isEmpty()  || volume.isEmpty() || expDate.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            databaseReference = FirebaseDatabase.getInstance().getReference("Pesticide Directory")
            val pesticides = PesticideData(name, cropType, price, volume, expDate)
            databaseReference.child(name).setValue(pesticides).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadCropType.text.clear()
                binding.uploadPrice.text.clear()
                binding.uploadVolume.text.clear()
                binding.uploadExpDate.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@PestUploadActivity, PestMainActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}