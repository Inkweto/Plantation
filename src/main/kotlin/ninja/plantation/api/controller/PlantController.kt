
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.http.ResponseEntity
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpHeaders;

import ninja.plantation.api.model.Plant
import ninja.plantation.api.model.User
import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.PlantRepository
import ninja.plantation.api.repository.UserRepository
import ninja.plantation.api.services.PlantService

@RestController
@CrossOrigin(origins = ["http://localhost:8000"],
                allowCredentials = "true")
@RequestMapping("/plants")
class PlantController(private val plantService: PlantService) {
	
    @Autowired
    lateinit var repository: PlantRepository

    @Autowired
    lateinit var userRepo: UserRepository
       
    @PostMapping("")
    fun process(@CookieValue("jwt") jwt: String?, @RequestBody newPlant : Plant): ResponseEntity<Any> {
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));


            //val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body;
            //newPlant.ownerId = body.issuer.toLong();
        return ResponseEntity.ok(plantService.addPlant(newPlant));
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }
       
    @GetMapping("/")
    fun findAllWhichBelongsToUserWithId(@CookieValue("jwt") jwt: String?, @RequestBody userId: User): ResponseEntity<Any> {
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));

            return ResponseEntity.ok(this.plantService.getPlantByOwnerId(userId));
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }
              
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Plant{
        return repository.findById(id).orElse(null)
    }

    @GetMapping("/{id}/notices")
    fun findByIdNotices(@PathVariable id: Long): List<Notice>{
        return repository.findById(id).orElse(null).notices.orEmpty()
    }

    @RequestMapping("/findPlantByName")
    fun fetchDataByName(@RequestParam("name") name: String): String{
        var result = ""
           
        for(cust in repository.findByName(name)){
            result += cust.toString() + "" 
        }
           
        return result
    }

    data class MsgResponse(val msg: String?)
}
