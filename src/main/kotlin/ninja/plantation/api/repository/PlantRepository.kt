package ninja.plantation.api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.management.PlatformManagedObject

import ninja.plantation.api.model.Plant
// CRUD functions for class Plant
@Repository
interface PlantRepository: CrudRepository<Plant, Long> {
    fun findByName(name: String): MutableList<Plant>
    fun findByOwnerId(user_owner_id: Long): List<Plant>
}