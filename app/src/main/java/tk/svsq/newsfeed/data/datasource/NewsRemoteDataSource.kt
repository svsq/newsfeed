package tk.svsq.newsfeed.data.datasource

import tk.svsq.newsfeed.Constants.KEYWORD_ANDROID
import tk.svsq.newsfeed.api.NewsApiService
import tk.svsq.newsfeed.api.common.BaseDataSource
import tk.svsq.newsfeed.api.responses.NewsListResponse
import tk.svsq.newsfeed.data.Result
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val apiService: NewsApiService): BaseDataSource() {

    suspend fun fetchNews(apiKey: String, page: Int, pageSize: Int): Result<NewsListResponse> =
        getResult { apiService.getTopNewsList(apiKey, page, pageSize, KEYWORD_ANDROID) }
}
