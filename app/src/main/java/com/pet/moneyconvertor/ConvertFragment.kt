package com.pet.moneyconvertor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pet.moneyconvertor.room.CurrencyEntity
import timber.log.Timber


class ConvertFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_convert, container, false)
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
}