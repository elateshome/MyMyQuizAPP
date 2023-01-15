package com.quizeapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//Contains the database holder and serves as the main access
//point for the underlying connection to my app's persisted, relational
//data.
@Database(
    entities = [Quiz::class],//we have only 1 table
    version = 1,
    exportSchema = true
)
abstract class QuizDB : RoomDatabase() {
    abstract fun getQuizDao(): QuizDAO //need to get the DAO for the entities

    // Build RoomDB
    companion object {  // static object to make singleton by campanion
        // visible to other threads
        @Volatile
        private var instance: QuizDB? = null
        private val LOCK = Any()
        //        buildDatabase
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context): QuizDB {
            return Room.databaseBuilder(
                context.applicationContext,
                QuizDB::class.java,
                "quizDB"
            ).build()
        }
    }

}