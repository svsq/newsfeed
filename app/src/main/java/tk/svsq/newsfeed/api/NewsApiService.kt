package tk.svsq.newsfeed.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tk.svsq.newsfeed.Constants.API_VERSION
import tk.svsq.newsfeed.api.responses.NewsListResponse

interface NewsApiService {

    @GET("/$API_VERSION/everything")
    suspend fun getTopNewsList(
        @Query("apiKey") apiKey: String? = null, @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("q") source: String? = null
    ): Response<NewsListResponse>

}