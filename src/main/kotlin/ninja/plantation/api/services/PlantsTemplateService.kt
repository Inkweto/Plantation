package ninja.plantation.api.services

import ninja.plantation.api.model.PlantsTemplate

interface PlantsTemplateService {


    fun addPlantsTemplate(this_PlantsTemplate: PlantsTemplate): PlantsTemplate
    fun deletePlantsTemplate(PlantsTemplate_id: Long)
    fun deleteAllPlantsTemplates()
    fun getPlantsTemplateById(PlantsTemplate_id: Long): PlantsTemplate?
    fun getPlantsTemplates(): MutableIterable<PlantsTemplate>?
    fun updatePlantsTemplate(PlantsTemplate_id: Long, this_PlantsTemplate: PlantsTemplate): PlantsTemplate
}