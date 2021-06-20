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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.config.BootstrapMode
import org.springframework.context.annotation.Bean
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@SpringBootApplication
class ApiApplication {
}

@EnableWebSecurity
class KotlinSecurityConfiguration : WebSecurityConfigurerAdapter() {
	override fun configure(http: HttpSecurity?) {
		http {
			csrf {
				disable()
			}
			httpBasic {}
			authorizeRequests {
				authorize("/notices/**", hasAuthority("ROLE_LOGGED"))
				authorize("/users/**", hasAuthority("ROLE_LOGGED"))
				authorize("/plants/**", hasAuthority("ROLE_LOGGED"))
				authorize("/plants-templates/**", hasAuthority("ROLE_LOGGED"))
			}
		}
	}
}

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args) {
        addInitializers(beans {
			bean {
				fun user(user: String, pw: String, vararg roles: String) =
                    User.withDefaultPasswordEncoder().username(user).password(pw).roles(*roles).build()

				InMemoryUserDetailsManager(user("user", "pw", "LOGGED"), 
										   user("admin", "pw1", "USER", "ADMIN")
										  )
			}
			bean {
				router {
					GET("/greetings") { request ->
						request.principal().map { it.name }.map { ServerResponse.ok().body(mapOf("greeting" to "Hello, $it")) }.orElseGet { ServerResponse.badRequest().build() }
					}
				}
			}
		})
    }
    //SpringApplication.run(ApiApplication::class.java, * args) 
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
