package tk.svsq.newsfeed.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class NewsListModel  (

    @PrimaryKey
    @SerializedName("title")
    var title: String = "",
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @Embedded @SerializedName("source") val source : Source? = null )