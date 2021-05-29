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
public class Plant(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var plant_id: Long? = null, 

    @Column(name = "user_owner_id")
    var user_owner_id: Long? = null,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "heigth")
    var heigth: Long? = 0,

    @Column(name = "diameter")
    var diameter: Long? = 0,

    @Column(name = "photo_path")
    var photo_path: String? = null
)