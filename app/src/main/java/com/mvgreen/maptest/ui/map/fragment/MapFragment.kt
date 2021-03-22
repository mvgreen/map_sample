package com.mvgreen.maptest.ui.map.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mvgreen.maptest.databinding.FragmentMapContainerBinding
import com.mvgreen.maptest.domain.entity.Geotag
import com.mvgreen.maptest.internal.di.DI
import com.mvgreen.maptest.ui.base.BaseFragment
import com.mvgreen.maptest.ui.map.viewmodel.MapViewModel
import com.mvgreen.maptest.utils.viewModelFactory

class MapFragment : BaseFragment<FragmentMapContainerBinding>(), OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModels(factoryProducer = {
        viewModelFactory {
            DI.appComponent.mapViewModel()
        }
    })

    override fun initBinding(inflater: LayoutInflater) {
        binding = FragmentMapContainerBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.map.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.map.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        binding.map.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.map.onStop()
    }

    override fun onMapReady(map: GoogleMap?) {
        val geotag = requireArguments().get("geotag") as Geotag
        val coordinates = LatLng(geotag.latitude!!, geotag.longitude!!)
        map?.addMarker(MarkerOptions().position(coordinates).title(geotag.name))
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15f))
        binding.map.onResume()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment().apply { arguments = Bundle() }
    }

}