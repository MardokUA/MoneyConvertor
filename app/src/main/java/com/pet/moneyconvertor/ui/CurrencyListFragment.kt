package com.pet.moneyconvertor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pet.moneyconvertor.R
import com.pet.moneyconvertor.adapters.CurrencyAdapter
import com.pet.moneyconvertor.databinding.FragmentCurrencyListBinding
import com.pet.moneyconvertor.viewmodelfactories.ConvertViewModelFactory
import com.pet.moneyconvertor.viewmodelfactories.CurrencyListViewModelFactory
import com.pet.moneyconvertor.viewmodels.ConvertViewModel
import com.pet.moneyconvertor.viewmodels.CurrencyListViewModel
import timber.log.Timber


class CurrencyListFragment : Fragment() {
    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrencyListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, CurrencyListViewModelFactory(activity.application)).get(
            CurrencyListViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyListBinding.inflate(layoutInflater, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        binding.currencyList.adapter = CurrencyAdapter(CurrencyAdapter.OnClickListener {
            Timber.v("click")
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}