package ninja.plantation.api.repository

import ninja.plantation.api.model.PlantsTemplate
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.management.PlatformManagedObject

// CRUD functions for class PlantsTemplate
@Repository
interface PlantsTemplateRepository: CrudRepository<PlantsTemplate, Long> {
}