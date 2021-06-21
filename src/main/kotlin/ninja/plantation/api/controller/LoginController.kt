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
import javax.servlet.http.HttpServletResponse
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import javax.servlet.http.Cookie
import org.springframework.web.bind.annotation.CookieValue;

@RestController
@CrossOrigin(origins = ["http://localhost:8000"])
@RequestMapping("/")
class LoginController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestParam("login") login: String, @RequestParam("password") password: String): ResponseEntity<Any> {
        val user = User();
        user.login = login;

        val passwordEncoder = BCryptPasswordEncoder();
        user.password_hash = passwordEncoder.encode(password);
        return ResponseEntity.ok(this.userService.addUser(user));
    }

    @PostMapping("/login")
    fun login(@RequestParam("login") login: String, @RequestParam("password") password: String, response: HttpServletResponse): ResponseEntity<Any> {
        val user = this.userService.getUserByLogin(login);

        if (user == null)
            return ResponseEntity.status(404).body(MsgResponse("User not found"));
        
        if (!BCryptPasswordEncoder().matches(password, user.password_hash))
            return ResponseEntity.status(403).body(MsgResponse("Bad password"));
        else {
        val issuer = user.id.toString();
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/user")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
            
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            return ResponseEntity.ok(this.userService.getUserById(body.issuer.toLong()));
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        var cookie = Cookie("jwt", "");
        cookie.maxAge = 0;
        response.addCookie(cookie);
        return ResponseEntity.ok(MsgResponse("Logged out"));
    }

    data class MsgResponse(val msg: String?)
}
