package com.mvgreen.maptest.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvgreen.maptest.ui.base.Event
import com.mvgreen.maptest.ui.base.EventsQueue
import java.util.*

fun <T> MutableLiveData<T>.onNext(next: T) {
    this.value = next
}

fun EventsQueue.onNext(next: Event) {
    this.offer(next)
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

inline fun <reified T : Event> Fragment.observeEvents(
    eventsQueue: EventsQueue,
    noinline block: (T) -> Unit
) {
    eventsQueue.observe(
        this.viewLifecycleOwner,
        { queue: Queue<Event>? ->
            while (queue != null && queue.isNotEmpty()) {
                block.invoke(queue.poll() as T)
            }
        }
    )
}