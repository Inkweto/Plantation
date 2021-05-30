package ninja.plantation.api.services

import ninja.plantation.api.model.Plant
import ninja.plantation.api.repository.PlantRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class PlantServiceImpl: PlantService {

    private val log = KotlinLogging.logger {}
    @Autowired
    lateinit var repository: PlantRepository

    override fun getPlants() =
            repository.findAll()

     override fun getPlantById(plant_id: Long) =
            repository.findById(plant_id).orElse(null);

    override fun getPlantByOwnerId(user_owner_id: Long) =
            repository.findByOwnerId(user_owner_id) 

    override fun getPlantByName(name: String) =
            repository.findByName(name)

    override fun addPlant(plant: Plant): Plant {
        repository.save(plant)
        log.info("${plant.name},added!!!")
        return plant
    }

    override fun deletePlant(plant_id: Long) {
        repository.deleteById(plant_id)
        log.info("Plant deleted!!")
    }

    override fun deleteAllPlants() {
        repository.deleteAll()
        log.info("All Plants deleted!!")
    }

    override fun updatePlant(plant_id: Long, plant: Plant): Plant {
        val currentPlant = repository.findById(plant_id).orElse(null);
        if (currentPlant != null) 
            repository.save(currentPlant)
        log.info("${plant.name},updated!!!")
      return currentPlant

    }

}
