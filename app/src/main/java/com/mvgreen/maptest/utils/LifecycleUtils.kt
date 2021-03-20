package com.mvgreen.maptest.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

fun <T> MutableLiveData<T>.onNext(next: T) {
    this.value = next
}

inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
    }
}

inline fun <reified T : ViewModel>
        FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory? = null): T {
    return ViewModelProvider(
        this,
        viewModelFactory ?: defaultViewModelProviderFactory
    )[T::class.java]
}

inline fun <reified T : ViewModel>
        Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory? = null): T {
    return ViewModelProvider(
        this,
        viewModelFactory ?: defaultViewModelProviderFactory
    )[T::class.java]
}

inline fun <reified T : Any, reified L : LiveData<T?>> FragmentActivity.observe(
    liveData: L,
    noinline block: (T) -> Unit
) {
    liveData.observe(this, Observer { it?.let { block.invoke(it) } })
}

inline fun <reified T : Any, reified L : LiveData<T?>> Fragment.observe(
    liveData: L,
    noinline block: (T) -> Unit
) {
    liveData.observe(this.viewLifecycleOwner, Observer { it?.let { block.invoke(it) } })
}
