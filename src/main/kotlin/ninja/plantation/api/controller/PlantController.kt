
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ninja.plantation.api.model.Plant
import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.PlantRepository
import ninja.plantation.api.repository.UserRepository

@RestController
@RequestMapping("/plants")
class PlantController {
	
    @Autowired
    lateinit var repository: PlantRepository

    @Autowired
    lateinit var userRepo: UserRepository
       
    @PostMapping("")
    fun process(@RequestBody newPlant : Plant): Plant{
        return repository.save(newPlant)
    }
       
       
    @GetMapping("")
    fun findAll(): Iterable<Plant>{  
        return repository.findAll()
    }
       
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Plant{
        return repository.findById(id).orElse(null)
    }

    @GetMapping("/{id}/notices")
    fun findByIdNotices(@PathVariable id: Long): List<Notice>{
        return repository.findById(id).orElse(null).notices.orEmpty()
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
