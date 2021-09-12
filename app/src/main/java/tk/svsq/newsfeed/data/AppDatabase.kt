package tk.svsq.newsfeed.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tk.svsq.newsfeed.data.dao.NewsDao
import tk.svsq.newsfeed.data.model.NewsListModel

@Database(entities = [NewsListModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "news-database")
                .build()
    }

    abstract fun getNewsListDao(): NewsDao
}