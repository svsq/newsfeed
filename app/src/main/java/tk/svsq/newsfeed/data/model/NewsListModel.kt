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
    @Expose
    @SerializedName("title")
    var title: String = "",
    @Expose
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @Expose
    @SerializedName("description")
    var description: String? = null,
    @Expose
    @SerializedName("author")
    var author: String? = null,
    @Expose
    @SerializedName("url")
    var url: String? = null,
    @Expose
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @Embedded @SerializedName("source") val source : Source? = null ) : Serializable