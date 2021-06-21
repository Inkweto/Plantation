package ninja.plantation.api.services

import ninja.plantation.api.model.User

interface UserService {


    fun addUser(user: User): User
    fun deleteUser(id: Long)
    fun deleteAllUsers()
    fun getUserById(id: Long): User?
    fun getUsers(): MutableIterable<User>?
    fun getUserByLogin(login: String): User?
    fun updateUser(id: Long, user: User): User
}