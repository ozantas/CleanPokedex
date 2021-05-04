package com.ozan.cleanpokedex.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner, observer: (value: T) -> Unit) {
    this.observe(lifecycleOwner, { data ->
        data?.let { observer.invoke(data) }
    })
}

inline fun <reified T> Any.postTo(liveData: MutableLiveData<T>) {
    if (this is T) liveData.postValue(this)
}