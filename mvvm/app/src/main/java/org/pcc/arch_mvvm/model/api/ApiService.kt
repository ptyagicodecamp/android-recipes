package org.pcc.arch_mvvm.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //"repository_search_url": "https://api.github.com/search/repositories?q={query}{&page,per_page,sort,order}",
    @GET("search/repositories")
    fun getListing(@Query("q") search: String = "android",
                   @Query("sort") sort: String = "stars") : Call<ApiResponse>
}