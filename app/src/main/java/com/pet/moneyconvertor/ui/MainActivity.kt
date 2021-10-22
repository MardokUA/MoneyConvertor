package com.pet.moneyconvertor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pet.moneyconvertor.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MoneyConvertor)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}