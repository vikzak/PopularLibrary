package ru.gb.popularlibrary.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.*

class SingleEventsLiveData<T>:MutableLiveData<T>()   {

    override fun getValue(): T? {
        val currentValue = super.getValue()
        if (currentValue!=null){
            postValue(null)
        }
        return currentValue
    }

}