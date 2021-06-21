
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

import ninja.plantation.api.model.PlantsTemplate
import ninja.plantation.api.repository.PlantsTemplateRepository

@RestController
@CrossOrigin(origins = ["http://localhost:8000"])
@RequestMapping("/plants-templates")
class PlantsTemplateController {
	
    @Autowired
    lateinit var repository: PlantsTemplateRepository
       
    @PostMapping("")
    fun process(@RequestBody newPlantsTemplate : PlantsTemplate): PlantsTemplate{
        return repository.save(newPlantsTemplate)
    }
       
       
    @GetMapping("")
    fun findAll(): Iterable<PlantsTemplate>{
        return repository.findAll()
    }
       
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): PlantsTemplate{
        return repository.findById(id).orElse(null)
    }
}
