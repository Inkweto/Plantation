package ninja.plantation.api.services

import ninja.plantation.api.model.Plant

interface PlantService {


    fun addPlant(plant: Plant): Plant
    fun deletePlant(plant_id: Long)
    fun deleteAllPlants()
    fun getPlantById(plant_id: Long): Plant?
    fun getPlants(): MutableIterable<Plant>?
    fun getPlantByName(name: String): List<Plant>
    fun getPlantByOwnerId(user_owner_id: Long): List<Plant>
    fun updatePlant(plant_id: Long, plant: Plant): Plant
}