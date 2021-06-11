package tk.svsq.newsfeed.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsListResponse(
    @Expose
    @SerializedName("source")
    private var source: String? = null,
    @Expose
    @SerializedName("status")
    private var status: String? = null,
    @Expose
    @SerializedName("articles")
    internal var articles: List<NewsListModel>) : Serializable