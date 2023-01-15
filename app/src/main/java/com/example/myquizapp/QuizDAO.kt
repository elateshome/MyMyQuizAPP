package com.quizeapp

import androidx.room.*

//Data access object. A mapping of SQL queries to functions. When you use a DAO, you
//call the methods, and Room takes care of the rest
@Dao
interface QuizDAO {
  //room DB does not run in the main thread that why i used Suspend keyword.

    //The keyword suspend is Kotlin's way of marking a function, or function type, available to
    //coroutines. When a coroutine calls a function marked suspend, instead of blocking until that
    //function returns like a normal function call, it suspends execution until the result is ready then
    //it resumes where it left off with the result.
    @Insert
    suspend fun addQuizes(vararg quiz: Quiz)  // to add multiple quiz to the table i used vararg.

    @Query("SELECT * FROM quiz ORDER BY id")
    suspend fun getAllQuizes(): List<Quiz>

    @Query("DELETE FROM quiz WHERE 1=1")
    suspend fun deleteAllQuiz()
}