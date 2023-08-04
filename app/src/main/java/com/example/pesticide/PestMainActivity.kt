package com.example.pesticide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pesticide.databinding.ActivityMainBinding

class PestMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainAdd.setOnClickListener {
            val intent = Intent(this@PestMainActivity, PestUploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this@PestMainActivity, PestUpdateActivity::class.java)
            startActivity(intent)

        }

        binding.mainDelete.setOnClickListener{
            val intent = Intent(this@PestMainActivity, PestDeleteActivity::class.java)
            startActivity(intent)

        }

        binding.mainCalender.setOnClickListener{
            val intent = Intent(this@PestMainActivity, CalenderActivity::class.java)
            startActivity(intent)

        }

        binding.mainBudget.setOnClickListener{
            val intent = Intent(this@PestMainActivity, BudgetActivity::class.java)
            startActivity(intent)

        }

        binding.mainDosage.setOnClickListener{
            val intent = Intent(this@PestMainActivity, DosageCalcActivity::class.java)
            startActivity(intent)

        }

        binding.mainSearch.setOnClickListener{
            val intent = Intent(this@PestMainActivity, PestViewActivity::class.java)
            startActivity(intent)
            
        }
    }
}