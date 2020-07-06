package com.shalaga44.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User): Int

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("DELETE FROM User_data_table")
    suspend fun deleteAllUsers():Int

    @Query("SELECT * FROM User_data_table")
    fun getAllUsers(): LiveData<List<User>>
}