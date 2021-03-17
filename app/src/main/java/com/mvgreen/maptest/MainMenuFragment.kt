package com.mvgreen.maptest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvgreen.maptest.databinding.FragmentMainMenuBinding
import com.mvgreen.maptest.ui.base.BaseFragment

class MainMenuFragment : BaseFragment() {

    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val layout = inflater.inflate(R.layout.fragment_main_menu, container, false)
        binding = FragmentMainMenuBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainMenuFragment().apply { arguments = Bundle() }
    }
}