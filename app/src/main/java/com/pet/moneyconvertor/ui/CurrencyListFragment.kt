package com.pet.moneyconvertor.ui

import SharedLeftViewModel
import SharedRightViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pet.moneyconvertor.adapters.CurrencyAdapter
import com.pet.moneyconvertor.databinding.FragmentCurrencyListBinding
import com.pet.moneyconvertor.viewmodelfactories.CurrencyListViewModelFactory
import com.pet.moneyconvertor.viewmodels.CurrencyListViewModel
import timber.log.Timber


class CurrencyListFragment : Fragment() {
    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    private val sharedLeftModel: SharedLeftViewModel by activityViewModels()
    private val sharedRightModel: SharedRightViewModel by activityViewModels()

    val args: CurrencyListFragmentArgs by navArgs()
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
        val side = args.selectedSide
        Timber.v(side)
        binding.currencyList.adapter = CurrencyAdapter(CurrencyAdapter.OnClickListener {
            item -> when(side) {
                "Left" -> sharedLeftModel.select(item)
                "Right" -> sharedRightModel.select(item)
            }
            findNavController().navigate(CurrencyListFragmentDirections.actionCurrencyListFragmentToConvertFragment())
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}