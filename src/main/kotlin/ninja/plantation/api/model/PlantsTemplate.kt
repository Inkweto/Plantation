package ninja.plantation.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity

@Table
@org.springframework.data.relational.core.mapping.Table

public class PlantsTemplate(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_plant_id", nullable = false, updatable = false)
    var template_plant_id: Long? = null, 

    @Column(name = "name")
    var name: String = "",
    
    @Column(name = "heigth")
    var heigth: Long = 0,

    @Column(name = "diameter")
    var diameter: String? = null,

    @Column(name = "photo_path")
    var photo_path: String? = null
)