package ninja.plantation.api.services

import ninja.plantation.api.model.User
import ninja.plantation.api.repository.UserRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class UserServiceImpl: UserService {

    private val log = KotlinLogging.logger {}
    @Autowired
    lateinit var repository: UserRepository

    override fun getUsers() =
            repository.findAll()

     override fun getUserById(id: Long) =
             repository.findById(id).orElse(null);

    override fun getUserByLogin(login: String) =
            repository.findByLogin(login)


    override fun addUser(user: User): User {
        return repository.save(user)
    }

    override fun deleteUser(id: Long) {
        repository.deleteById(id)
        log.info("user deleted!!")
    }

    override fun deleteAllUsers() {
        repository.deleteAll()
        log.info("All users deleted!!")
    }

    override fun updateUser(id: Long, user: User):User {
        val currentUser = repository.findById(id).orElse(null);
       //  if (currentUser != null) repository.save(currentUser.copy(id,user.login,user.password_hash))
        //log.info("${user.login},updated!!!")
      return currentUser

    }

}
