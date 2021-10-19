package com.pet.moneyconvertor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider


class ConvertFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelFactory = ConvertViewModelFactory()
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ConvertViewModel::class.java)
        viewModel.fetchValCurs()
        return inflater.inflate(R.layout.fragment_convert, container, false)
    }

}