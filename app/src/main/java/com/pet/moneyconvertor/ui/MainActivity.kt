package com.pet.moneyconvertor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pet.moneyconvertor.R
import com.pet.moneyconvertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MoneyConvertor)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
    }
}