
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import ninja.plantation.api.model.Plant
import ninja.plantation.api.repository.PlantRepository
import ninja.plantation.api.repository.UserRepository

@RestController
class PlantController {
	
    @Autowired
    lateinit var repository: PlantRepository

    @Autowired
    lateinit var userRepo: UserRepository
       
    @RequestMapping("/savePlants")
    fun process(): String{
        
        repository.save(Plant(name="Aloe"))
        repository.save(Plant(name="Cactus"))
        repository.save(Plant(name="Petunie"))
        return "Done"
    }
       
       
    @RequestMapping("/FindAllPlants")
    fun findAll(): String{
        var result = ""
		
        for(cust in repository.findAll()){
            result += cust.toString() + ""
        }
           
        return result
    }
       
    @RequestMapping("/FindPlantById")
    fun findById(@RequestParam("id") id: Long): String{
        return repository.findById(id).orElse(null).toString()
    }
       
    @RequestMapping("/FindPlantByOwnerId")
    fun findByOwnerId(@RequestParam("owenrId") ownerId: Long): String{
        return repository.findByOwnerId(ownerId).toString()
    }

    @RequestMapping("/findPlantByName")
    fun fetchDataByName(@RequestParam("name") name: String): String{
        var result = ""
           
        for(cust in repository.findByName(name)){
            result += cust.toString() + "" 
        }
           
        return result
    }

    
}
