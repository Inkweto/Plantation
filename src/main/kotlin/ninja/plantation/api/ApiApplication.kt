package ninja.plantation.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.boot.SpringApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("ninja.plantation.api.controller")
@EnableJpaRepositories("ninja.plantation.api.repository")
// @EntityScan("ninja.plantation.api.model")
@EnableJpaAuditing
class ApiApplication {
}

fun main(args: Array<String>) {
    //runApplication<ApiApplication>(*args)
    SpringApplication.run(ApiApplication::class.java, * args)
}

@RestController
class MessageResource {
    @GetMapping
    fun index(): List<Message> = listOf(
        Message("1", "The future"),
        Message("2", "is"),
        Message("3", "bright!"),
    )
}

data class Message(val id: String?, val text: String)
