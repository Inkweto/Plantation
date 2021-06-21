package ninja.plantation.api.controller

import ninja.plantation.api.model.User
import ninja.plantation.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.hibernate.annotations.NotFound
import org.springframework.web.bind.annotation.CrossOrigin;
import com.sun.istack.Nullable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.ResponseErrorHandler

@RestController
@CrossOrigin(origins = ["http://localhost:8000"])
@RequestMapping("/login")
class LoginController {

    @Autowired lateinit var repository: UserRepository

    @GetMapping("")
    fun login(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<UserResponse> {
        for (user in repository.findByLogin(login)) {
            if(user.password_hash.equals(password))
                return ResponseEntity<UserResponse>(UserResponse(user.login), HttpStatus.OK);
        }
        
        return ResponseEntity<UserResponse>(null, HttpStatus.NOT_FOUND);
    }

    data class UserResponse(val name: String?)
}
