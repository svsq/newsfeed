package tk.svsq.newsfeed.api.responses

import com.google.gson.annotations.SerializedName
import tk.svsq.newsfeed.data.model.NewsListModel

data class NewsListResponse(
    @SerializedName("source")
    private var source: String? = null,
    @SerializedName("status")
    private var status: String? = null,
    @SerializedName("articles")
    internal var articles: List<NewsListModel>)
