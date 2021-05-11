
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import ninja.plantation.api.model.User
import ninja.plantation.api.repository.UserRepository

@RestController
class WebController {
	
    @Autowired
    lateinit var repository: UserRepository
       
    @RequestMapping("/save")
    fun process(): String{
        repository.save(User(1, "Jack", "pass1"))
        repository.save(User(2, "Adam", "admin"))
        repository.save(User(3, "Kim", "pass2"))
        return "Done"
    }
       
       
    @RequestMapping("/findall")
    fun findAll(): String{
        var result = ""
		
        for(cust in repository.findAll()){
            result += cust.toString() + ""
        }
           
        return result
    }
       
    @RequestMapping("/findbyid")
    fun findById(@RequestParam("id") id: Long): String{
        return repository.findById(id).orElse(null).toString()
    }
       
    @RequestMapping("/findbylogin")
    fun fetchDataBylogin(@RequestParam("login") login: String): String{
        var result = ""
           
        for(cust in repository.findByLogin(login)){
            result += cust.toString() + "" 
        }
           
        return result
    }
}
