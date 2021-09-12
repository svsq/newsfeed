package tk.svsq.newsfeed.api.common

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Data<T : Any>(
    val pagedData: LiveData<PagedList<T>>, // TODO: Refactor to PagingData
    val networkState: LiveData<NetworkState>
)
