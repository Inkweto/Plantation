
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import ninja.plantation.api.model.PlantsTemplate
import ninja.plantation.api.repository.PlantsTemplateRepository

@RestController
class PlantsTemplateController {
	
    @Autowired
    lateinit var repository: PlantsTemplateRepository
       
    @RequestMapping("/savePlantsTemplate")
    fun process(): String{
        return "Done"
    }
       
       
    @RequestMapping("/findAllPlantsTemplates")
    fun findAll(): String{
        var result = ""
		
        for(cust in repository.findAll()){
            result += cust.toString() + ""
        }
           
        return result
    }
       
    @RequestMapping("/findPlantsTemplatebyid")
    fun findById(@RequestParam("id") id: Long): String{
        return repository.findById(id).orElse(null).toString()
    }
}
