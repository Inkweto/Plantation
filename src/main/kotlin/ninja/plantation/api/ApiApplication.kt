package ninja.plantation.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ApiApplication {
}

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
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
