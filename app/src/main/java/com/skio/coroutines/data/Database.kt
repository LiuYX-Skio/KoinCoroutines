package com.skio.coroutines.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skio.coroutines.data.db.HomeArticleDetail
import com.skio.coroutines.data.db.HomeArticleRemoteKey

/**
 * @author kuky.
 * @description
 */

@Database(
    entities = [HomeArticleDetail::class, HomeArticleRemoteKey::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun homeArticleCacheDao(): HomeArticleCacheDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
          AppDatabase::class.java, "app.db"
        ).build()
    }
}
