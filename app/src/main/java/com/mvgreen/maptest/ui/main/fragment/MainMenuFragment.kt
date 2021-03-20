package com.mvgreen.maptest.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.mvgreen.maptest.R
import com.mvgreen.maptest.domain.entity.Geotag
import com.mvgreen.maptest.internal.di.DI
import com.mvgreen.maptest.ui.base.BaseFragment
import com.mvgreen.maptest.ui.main.viewmodel.MainMenuViewModel
import com.mvgreen.maptest.utils.viewModelFactory

class MainMenuFragment : BaseFragment() {

    private val viewModel: MainMenuViewModel by viewModels(factoryProducer = {
        viewModelFactory {
            DI.appComponent.mainMenuViewModel()
        }
    })

    override fun setupViewModel() {
        viewModel.getLiveGeotagList().observe(this, {
            updateChipList(it)
        })
        binding.btnAddPoint.setOnClickListener {
            viewModel.onAddGeotag(
                binding.etPointAddress.text.toString(),
                binding.etPointName.text.toString()
            )
        }
        
        viewModel.onLoadGeotagList()
    }

    private fun updateChipList(list: List<Geotag>) {
        val group = binding.chgroupSavedPoints
        group.removeAllViews()
        for (item in list) {
            val chip = Chip(requireContext())
                .apply {
                    text = item.name
                    setOnClickListener {
                        showSnackbar(getString(R.string.message_placeholder_action))
                    }
                }
            group.addView(chip)
            group.invalidate()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainMenuFragment().apply { arguments = Bundle() }
    }
}