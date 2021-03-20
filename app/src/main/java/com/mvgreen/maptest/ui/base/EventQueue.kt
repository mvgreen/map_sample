package com.mvgreen.maptest.ui.base

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import java.lang.Exception
import java.util.*

class EventsQueue : MutableLiveData<Queue<Event>>() {

    @MainThread
    fun offer(event: Event) {
        val queue = (value ?: LinkedList()).apply {
            add(event)
        }

        value = queue
    }

}

abstract class Event

class GeocodeErrorEvent (val exception: Exception): Event()
