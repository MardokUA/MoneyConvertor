package com.pet.moneyconvertor.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pet.moneyconvertor.R
import com.pet.moneyconvertor.databinding.FragmentConvertBinding
import com.pet.moneyconvertor.viewmodelfactories.ConvertViewModelFactory
import com.pet.moneyconvertor.viewmodels.ConvertViewModel
import com.pet.moneyconvertor.viewmodels.SharedLeftViewModel
import com.pet.moneyconvertor.viewmodels.SharedRightViewModel


class ConvertFragment : Fragment() {
    private var _binding: FragmentConvertBinding? = null
    private val binding get() = _binding!!

    /*
     FIXME: 25.11.2021 one viewModel per ui component. If you need to share data between two or more fragments,
          you have two opportunities to achieve that:
          - the same ONE view model instead TWO ones;
          - use your repository. For example, you could have some InMemoryStorage in it.
     */
    private val sharedLeftModel: SharedLeftViewModel by activityViewModels()
    private val sharedRightModel: SharedRightViewModel by activityViewModels()

    private val viewModel: ConvertViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            // FIXME: 25.11.2021 swallowed message
        }
        ViewModelProvider(this, ConvertViewModelFactory(activity.application))
            .get(ConvertViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                    getString(R.string.left_button_navigation_key)
                )
            )
        }

        binding.buttonRight.setOnClickListener {
            findNavController().navigate(
                ConvertFragmentDirections.actionConvertFragmentToCurrencyListFragment(
                    getString(R.string.right_button_navigation_key)
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
        // TODO: 25.11.2021 it's better to write extension and hide all the boilerplate code
        binding.editTextValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.convert(p0.toString())
            }

        })

        binding.editTextValue.setOnClickListener {
            // FIXME: 25.11.2021 never ask, just tell principle
            if (viewModel.startInputValue.value != true) {
                Toast.makeText(
                    context,
                    getString(R.string.enabled_toast_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}