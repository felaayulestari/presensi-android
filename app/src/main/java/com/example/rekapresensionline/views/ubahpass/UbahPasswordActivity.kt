package com.example.rekapresensionline.views.ubahpass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rekapresensionline.R
import com.example.rekapresensionline.databinding.ActivityUbahPasswordBinding

class UbahPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUbahPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun onClick() {
        binding.tbChangePassword.setNavigationOnClickListener {
            finish()
        }
    }

    private fun init() {
        setSupportActionBar(binding.tbChangePassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}