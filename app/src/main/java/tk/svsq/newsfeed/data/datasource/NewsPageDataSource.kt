package tk.svsq.newsfeed.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tk.svsq.newsfeed.Constants.apiKey
import tk.svsq.newsfeed.api.common.NetworkState
import tk.svsq.newsfeed.data.Result
import tk.svsq.newsfeed.data.dao.NewsDao
import tk.svsq.newsfeed.data.model.NewsListModel
import javax.inject.Inject

class NewsPageDataSource @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val newsDao: NewsDao,
    private val coroutineScope: CoroutineScope
): PageKeyedDataSource<Int, NewsListModel>() { // TODO: refactor deprecated PageKeyedDataSource to PagingSource

    companion object {
        const val TAG = "NewsPageDataSource"
    }

    val networkState = MutableLiveData<NetworkState>()

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsListModel>) {
        networkState.postValue(NetworkState.LOADING)
        val page = params.key
        fetchData(page = page, pageSize = params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsListModel>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsListModel>
    ) {
        networkState.postValue(NetworkState.LOADING)
        fetchData(page = 1, pageSize = params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<NewsListModel>) -> Unit) {
        coroutineScope.launch(getJobErrorHandler()) {
            when (val response = remoteDataSource.fetchNews(apiKey, page, pageSize)) {
                is Result.Error -> {
                    networkState.postValue(NetworkState.error(response.message ?: "Unknown error"))
                    postError(response.message)
                }
                is Result.Success -> {
                    val results = response.data.articles
                    newsDao.insertAll(results)
                    callback(results)
                    networkState.postValue(NetworkState.LOADED)
                }
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, ex ->
        postError(ex.message ?: ex.toString())
    }

    private fun postError(message: String?) {
        Log.e(TAG, "Error occurred: $message")
    }

}