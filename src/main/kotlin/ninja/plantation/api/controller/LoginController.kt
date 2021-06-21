package ninja.plantation.api.controller

import ninja.plantation.api.model.User
import ninja.plantation.api.repository.UserRepository
import ninja.plantation.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.http.HttpStatus
import org.hibernate.annotations.NotFound
import org.springframework.web.bind.annotation.CrossOrigin;
import com.sun.istack.Nullable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.ResponseErrorHandler
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin(origins = ["http://localhost:8000"])
@RequestMapping("/conn")
class LoginController(private val userService: UserService) {

    @Autowired lateinit var repository: UserRepository

    @PostMapping("/register")
    fun register(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<User> {
        val user = User();
        user.login = login;

        val passwordEncoder = BCryptPasswordEncoder();
        user.password_hash = passwordEncoder.encode(password);
        return ResponseEntity.ok(this.userService.addUser(user));
    }

    @PostMapping("/login")
    fun login(@RequestParam("login") login: String, @RequestParam("password") password: String, response: HttpServletResponse): ResponseEntity<UserResponse> {
        val user = this.userService.getUserByLogin(login);

        if (user == null)
            return ResponseEntity<UserResponse>(null, HttpStatus.NOT_FOUND);
        
        if (!BCryptPasswordEncoder().matches(password, user.password_hash))
            return ResponseEntity<UserResponse>(null, HttpStatus.FORBIDDEN);
        else {
        val issuer = user.id.toString();
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity<UserResponse>(UserResponse(user.login), HttpStatus.OK);
        }
    }


    data class UserResponse(val name: String?)
}
