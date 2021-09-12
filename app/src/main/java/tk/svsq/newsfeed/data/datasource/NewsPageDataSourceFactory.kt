package tk.svsq.newsfeed.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import tk.svsq.newsfeed.Constants.PAGE_SIZE
import tk.svsq.newsfeed.data.dao.NewsDao
import tk.svsq.newsfeed.data.model.NewsListModel
import javax.inject.Inject

class NewsPageDataSourceFactory @Inject constructor(
    private val dataSource: NewsRemoteDataSource,
    private val dao: NewsDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, NewsListModel>() {

    companion object {
        fun pagedListConfig() = PagedList.Config.Builder() // TODO replace to PagingData
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

    val liveData = MutableLiveData<NewsPageDataSource>()

    override fun create(): DataSource<Int, NewsListModel> {
        val source = NewsPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }
}