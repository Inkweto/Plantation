package ninja.plantation.api.repository

import ninja.plantation.api.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

// CRUD functions for class User
@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}