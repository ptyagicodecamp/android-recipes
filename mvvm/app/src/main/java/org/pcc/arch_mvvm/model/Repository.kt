package org.pcc.arch_mvvm.model

import org.pcc.arch_mvvm.model.api.ApiClient
import org.pcc.arch_mvvm.model.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    //Get listing
    fun getListing(onResult: (isSuccess: Boolean, response: ApiResponse?) -> Unit) {

        ApiClient.instance.getListing().enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(false, null)
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response != null && response.isSuccessful) onResult(true, response.body()!!)
                else onResult(false, null)
            }

        }
        )
    }

    companion object {
        private var instance: Repository? = null
        fun getInstance() = instance ?: Repository().also { instance = it }
    }
}