package org.pcc.arch_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import org.pcc.arch_mvvm.model.Repository
import org.pcc.arch_mvvm.model.RowData

class ListingViewModel : BaseViewModel() {
    val liveListing = MutableLiveData<List<RowData>>()

    fun fetchListing() {
        loading.value = true
        Repository.getInstance().getListing { isSuccess, response ->
            loading.value = false
            if (isSuccess) {
                liveListing.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }

}