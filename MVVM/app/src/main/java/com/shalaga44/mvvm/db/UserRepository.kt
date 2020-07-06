package com.shalaga44.mvvm.db

class UserRepository(val dao: UserDAO) {
    val users = dao.getAllUsers()

    suspend fun insert(user: User): Long {
        return dao.insertUser(user)
    }

    suspend fun update(user: User): Int {
        return dao.updateUser(user)
    }

    suspend fun delete(user: User): Int {
        return dao.deleteUser(user)
    }

    suspend fun deleteALL(): Int {
        return dao.deleteAllUsers()
    }
}