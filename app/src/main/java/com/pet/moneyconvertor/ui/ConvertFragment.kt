package com.pet.moneyconvertor.ui

import SharedLeftViewModel
import SharedRightViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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

    private val sharedLeftModel: SharedLeftViewModel by activityViewModels()
    private val sharedRightModel: SharedRightViewModel by activityViewModels()

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

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonLeft.setOnClickListener {
            findNavController().navigate(
                ConvertFragmentDirections.actionConvertFragmentToCurrencyListFragment(
                    "Left"
                )
            )
        }

        binding.buttonRight.setOnClickListener {
            findNavController().navigate(
                ConvertFragmentDirections.actionConvertFragmentToCurrencyListFragment(
                    "Right"
                )
            )
        }

        sharedLeftModel.selected.observe(viewLifecycleOwner, Observer<CurrencyEntity> { currency ->
            viewModel.setLeftCurrency(currency)
        })
        sharedRightModel.selected.observe(viewLifecycleOwner, Observer<CurrencyEntity> { currency ->
            viewModel.setRightCurrency(currency)
        })

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}