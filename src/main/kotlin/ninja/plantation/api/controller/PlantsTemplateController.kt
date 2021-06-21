
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

import ninja.plantation.api.model.PlantsTemplate
import ninja.plantation.api.repository.PlantsTemplateRepository
import ninja.plantation.api.services.PlantsTemplateService

@RestController
@CrossOrigin(origins = ["http://localhost:8000"])
@RequestMapping("/plants-templates")
class PlantsTemplateController(private val plantsTemplateService: PlantsTemplateService) {
	
    @Autowired
    lateinit var repository: PlantsTemplateRepository
       
    @PostMapping("")
    fun process(@CookieValue("jwt") jwt: String?, @RequestBody newPlantsTemplate : PlantsTemplate): ResponseEntity<Any>{
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));          
            
            return ResponseEntity.ok(this.plantsTemplateService.addPlantsTemplate(newPlantsTemplate));
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }
       
       
    @GetMapping("")
    fun findAll(@CookieValue("jwt") jwt: String?): ResponseEntity<Any>{
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));   

        return ResponseEntity.ok(this.plantsTemplateService.getPlantsTemplates());
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }
       
    @GetMapping("/{id}")
    fun findById(@CookieValue("jwt") jwt: String?, @PathVariable id: Long): ResponseEntity<Any>{
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));   

        return ResponseEntity.ok(this.plantsTemplateService.getPlantsTemplateById(id));
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }

    data class MsgResponse(val msg: String?)
}
