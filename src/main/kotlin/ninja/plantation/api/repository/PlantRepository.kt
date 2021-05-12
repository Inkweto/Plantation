package ninja.plantation.api.repository

import ninja.plantation.api.model.Plant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.management.PlatformManagedObject

// CRUD functions for class Plant
@Repository
interface PlantRepository: CrudRepository<Plant, Long> {
    fun findByName(name: String): MutableList<Plant>
}