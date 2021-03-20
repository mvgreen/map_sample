package com.mvgreen.maptest.ui.main.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvgreen.maptest.domain.entity.Geotag
import com.mvgreen.maptest.domain.usecase.GeotagUseCase
import com.mvgreen.maptest.ui.base.BaseViewModel
import com.mvgreen.maptest.ui.base.GeocodeErrorEvent
import com.mvgreen.maptest.utils.onNext
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val geocodeUseCase: GeotagUseCase
) : BaseViewModel() {

    private val liveGeotagList: MutableLiveData<List<Geotag>> = MutableLiveData()

    fun getLiveGeotagList(): LiveData<List<Geotag>> = liveGeotagList

    @SuppressLint("CheckResult")
    fun onAddGeotag(address: String, name: String) {
        geocodeUseCase
            .addGeotag(Geotag(name, address))
            .subscribe(
                {},
                {
                    events.offer(GeocodeErrorEvent(it as Exception))
                }
            )
    }

    fun onLoadGeotagList() {
        geocodeUseCase
            .loadGeotagList()
            .subscribe(
                { newList ->
                    liveGeotagList.onNext(newList)
                },
                { ex ->
                    TODO(ex.message ?: "")
                }
            )
            .disposeOnViewModelDestroy()
    }

}