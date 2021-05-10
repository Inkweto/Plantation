package ninja.plantation.api.repository

import ninja.plantation.api.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String): MutableList<User>
}