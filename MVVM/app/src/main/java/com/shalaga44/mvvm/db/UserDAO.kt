package com.shalaga44.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM User_data_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM User_data_table")
    fun getAllUsers(): LiveData<User>
}