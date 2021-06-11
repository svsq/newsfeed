package tk.svsq.newsfeed.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tk.svsq.newsfeed.Constants.API_VERSION
import tk.svsq.newsfeed.data.model.NewsListResponse

interface NewsApi {

    @GET("/$API_VERSION/everything")
    suspend fun getTopNewsList(
        @Query("apiKey") apiKey: String? = null, @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("q") source: String? = null
    ): Response<NewsListResponse>

}