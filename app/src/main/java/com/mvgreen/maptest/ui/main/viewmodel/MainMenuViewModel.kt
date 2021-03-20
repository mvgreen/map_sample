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

    private val liveSearchInProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun getLiveGeotagList(): LiveData<List<Geotag>> = liveGeotagList

    fun getLiveSearchInProgress(): LiveData<Boolean> = liveSearchInProgress

    @SuppressLint("CheckResult")
    fun onAddGeotag(address: String, name: String) {
        if (liveSearchInProgress.value == true) {
            return
        }
        liveSearchInProgress.onNext(true)
        geocodeUseCase
            .addGeotag(Geotag(name, address))
            .subscribe(
                {
                    liveSearchInProgress.onNext(false)
                },
                {
                    events.offer(GeocodeErrorEvent(it as Exception))
                    liveSearchInProgress.onNext(false)
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