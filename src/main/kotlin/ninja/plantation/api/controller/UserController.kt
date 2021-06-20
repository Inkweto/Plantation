package ninja.plantation.api.controller

import ninja.plantation.api.model.User
import ninja.plantation.api.model.Plant
import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired lateinit var repository: UserRepository

    @PostMapping("")
    fun process(@RequestBody newUser: User): User {
        return repository.save(newUser)
    }

    @GetMapping("")
    fun findAll(): Iterable<User> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): User {
        return repository.findById(id).orElse(null)
    }

    @GetMapping("/{id}/plants")
    fun findByIdPlants(@PathVariable id: Long): List<Plant> {
        return repository.findById(id).orElse(null).plants.orEmpty()
    }

    @GetMapping("/{id}/notices")
    fun findByIdNotices(@PathVariable id: Long): List<Notice> {
        return repository.findById(id).orElse(null).notices.orEmpty()
    }

    @RequestMapping("/findUserByLogin")
    fun fetchDataBylogin(@RequestParam("login") login: String): String {
        var result = ""

        for (cust in repository.findByLogin(login)) {
            result += cust.toString() + ""
        }

        return result
    }
}
