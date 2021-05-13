
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import ninja.plantation.api.model.Plant
import ninja.plantation.api.repository.PlantRepository

@RestController
class Plantontroller {
	
    @Autowired
    lateinit var repository: PlantRepository
       
    @RequestMapping("/saveplants")
    fun process(): String{
        repository.save(Plant(7, 1, "plant_test", 3))
        return "Done"
    }
       
       
    @RequestMapping("/findallplants")
    fun findAll(): String{
        var result = ""
		
        for(cust in repository.findAll()){
            result += cust.toString() + ""
        }
           
        return result
    }
       
    @RequestMapping("/findplantbyid")
    fun findById(@RequestParam("id") id: Long): String{
        return repository.findById(id).orElse(null).toString()
    }
       
    @RequestMapping("/findplantbyname")
    fun fetchDataByName(@RequestParam("name") name: String): String{
        var result = ""
           
        for(cust in repository.findByName(name)){
            result += cust.toString() + "" 
        }
           
        return result
    }
}
