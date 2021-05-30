
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import ninja.plantation.api.model.User
import ninja.plantation.api.repository.UserRepository

@RestController
class UserController {
	
    @Autowired
    lateinit var repository: UserRepository
       
    @RequestMapping("/saveUsers")
    fun process(): String{

        repository.save(User("Adam", "admin"))
        repository.save(User("Kimss", "pass2"))
        repository.save(User("Adam1", "admi1n"))
        return "Done"
    }
       
       
    @RequestMapping("/findAll")
    fun findAll(): String{
        var result = ""
		
        for(cust in repository.findAll()){
            result += cust.toString() + ""
        }
           
        return result
    }
       
    @RequestMapping("/findUserById")
    fun findById(@RequestParam("id") id: Long): String{
        return repository.findById(id).orElse(null).toString()
    }
       
    @RequestMapping("/findUserByLogin")
    fun fetchDataBylogin(@RequestParam("login") login: String): String{
        var result = ""
           
        for(cust in repository.findByLogin(login)){
            result += cust.toString() + "" 
        }
           
        return result
    }
}
