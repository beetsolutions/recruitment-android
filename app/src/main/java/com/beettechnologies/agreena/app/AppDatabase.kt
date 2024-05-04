package com.beettechnologies.agreena.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beettechnologies.agreena.home.data.local.dao.CertificateDao
import com.beettechnologies.agreena.home.data.local.entity.CertificateEntity

@Database(
    entities = [CertificateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun certificateDao(): CertificateDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "agreena_db")
                .allowMainThreadQueries()
                .build()
        }
    }
}
