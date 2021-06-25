package ninja.plantation.api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.management.PlatformManagedObject

import ninja.plantation.api.model.Plant
import ninja.plantation.api.model.User
// CRUD functions for class Plant
@Repository
interface PlantRepository: CrudRepository<Plant, Long> {
    fun findByName(name: String): MutableList<Plant>
    fun findByOwnerId(ownerId: User?): List<Plant>
}