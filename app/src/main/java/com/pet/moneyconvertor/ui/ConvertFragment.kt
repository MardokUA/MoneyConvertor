package com.pet.moneyconvertor.ui

import SharedLeftViewModel
import SharedRightViewModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pet.moneyconvertor.databinding.FragmentConvertBinding
import com.pet.moneyconvertor.room.CurrencyEntity
import com.pet.moneyconvertor.viewmodelfactories.ConvertViewModelFactory
import com.pet.moneyconvertor.viewmodels.ConvertViewModel


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
        _binding = FragmentConvertBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        binding.convertButton.setOnClickListener {
            viewModel.swapCurrency(binding.editTextValue.text.toString())
        }
        sharedLeftModel.selected.observe(viewLifecycleOwner, { currency ->
            viewModel.setLeftCurrency(currency)
        })
        sharedRightModel.selected.observe(viewLifecycleOwner, { currency ->
            viewModel.setRightCurrency(currency)
        })

        binding.editTextValue.addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.convert(p0.toString())
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}