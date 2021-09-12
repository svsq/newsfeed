package tk.svsq.newsfeed.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import kotlinx.coroutines.CoroutineScope
import tk.svsq.newsfeed.api.common.Data
import tk.svsq.newsfeed.api.common.NetworkState
import tk.svsq.newsfeed.data.dao.NewsDao
import tk.svsq.newsfeed.data.datasource.NewsPageDataSourceFactory
import tk.svsq.newsfeed.data.datasource.NewsRemoteDataSource
import tk.svsq.newsfeed.data.model.NewsListModel
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsDao: NewsDao,
                                         private val newsRemoteDataSource: NewsRemoteDataSource
) {
    fun observePagedNews(connectivityAvailable : Boolean, coroutineScope: CoroutineScope)
            : Data<NewsListModel> {

        return if (connectivityAvailable)
            observeRemotePagedNews(coroutineScope)
        else observeLocalPagedNews()
    }
    private fun observeLocalPagedNews(): Data<NewsListModel> {

        val dataSourceFactory = newsDao.getPagedNews()

        val createLD = MutableLiveData<NetworkState>()
        createLD.postValue(NetworkState.LOADED)

        return Data(
            // TODO: Refactor to PagingData
            LivePagedListBuilder(dataSourceFactory,
            NewsPageDataSourceFactory.pagedListConfig()).build(),createLD)
    }

    private fun observeRemotePagedNews(ioCoroutineScope: CoroutineScope): Data<NewsListModel> {
        val dataSourceFactory = NewsPageDataSourceFactory(newsRemoteDataSource,
            newsDao, ioCoroutineScope)

        val networkState = Transformations.switchMap(dataSourceFactory.liveData) {
            it.networkState
        }
        return Data(LivePagedListBuilder(dataSourceFactory,
            NewsPageDataSourceFactory.pagedListConfig()).build(),networkState) // TODO: Refactor to PagingData
    }
}