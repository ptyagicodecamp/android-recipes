package org.pcc.arch_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val empty = MutableLiveData<Boolean>().apply { value = false }
    val loading = MutableLiveData<Boolean>().apply { value = false }
    val status = MutableLiveData<String>()

}