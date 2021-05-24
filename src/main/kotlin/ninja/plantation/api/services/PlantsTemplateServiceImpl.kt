package ninja.plantation.api.services

import ninja.plantation.api.model.PlantsTemplate
import ninja.plantation.api.repository.PlantsTemplateRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class PlantsTemplateServiceImpl: PlantsTemplateService {

    private val log = KotlinLogging.logger {}
    @Autowired
    lateinit var repository: PlantsTemplateRepository

    override fun getPlantsTemplates() =
            repository.findAll()

     override fun getPlantsTemplateById(PlantsTemplate_id: Long) =
             repository.findById(PlantsTemplate_id).orElse(null);


    override fun addPlantsTemplate(this_PlantsTemplate: PlantsTemplate): PlantsTemplate {
        repository.save(this_PlantsTemplate)
        log.info("${this_PlantsTemplate.name},added!!!")
        return this_PlantsTemplate
    }

    override fun deletePlantsTemplate(PlantsTemplate_id: Long) {
        repository.deleteById(PlantsTemplate_id)
        log.info("PlantsTemplate deleted!!")
    }

    override fun deleteAllPlantsTemplates() {
        repository.deleteAll()
        log.info("All PlantsTemplates deleted!!")
    }

    override fun updatePlantsTemplate(PlantsTemplate_id: Long, this_PlantsTemplate: PlantsTemplate):PlantsTemplate {
        val currentPlantsTemplate = repository.findById(PlantsTemplate_id).orElse(null);
        if (currentPlantsTemplate != null) 
            repository.save(currentPlantsTemplate)
        log.info("${this_PlantsTemplate.name},updated!!!")
      return currentPlantsTemplate

    }

}
