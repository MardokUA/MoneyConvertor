package com.pet.moneyconvertor.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pet.moneyconvertor.databinding.FragmentConvertBinding
import com.pet.moneyconvertor.room.CurrencyEntity
import com.pet.moneyconvertor.viewmodelfactories.ConvertViewModelFactory
import com.pet.moneyconvertor.viewmodels.ConvertViewModel
import timber.log.Timber


class ConvertFragment : Fragment() {
    private var _binding: FragmentConvertBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConvertViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, ConvertViewModelFactory(activity.application))
            .get(ConvertViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.fetchCurrencies()
        _binding = FragmentConvertBinding.inflate(layoutInflater, container, false)
        binding.buttonLeft.setOnClickListener {
            findNavController().navigate(ConvertFragmentDirections.actionConvertFragmentToCurrencyListFragment())
        }

        binding.buttonRight.setOnClickListener {
            findNavController().navigate(ConvertFragmentDirections.actionConvertFragmentToCurrencyListFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currencies.observe(
            viewLifecycleOwner,
            Observer<List<CurrencyEntity>> { currencies ->
                currencies?.apply {
                    Timber.v(currencies.toString())
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}