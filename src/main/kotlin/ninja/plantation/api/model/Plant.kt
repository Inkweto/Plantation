package ninja.plantation.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.JoinColumn

@Entity
@Table(name = "plant")
public class Plant(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var plant_id: Long? = null, 

    @ManyToOne(fetch = FetchType.EAGER)
    val users: User? = null,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "heigth")
    var heigth: Long? = 0,

    @Column(name = "diameter")
    var diameter: Long? = 0,

    @Column(name = "photo_path")
    var photo_path: String? = null,

    // @OneToMany(mappedBy = "plant", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    // var notices: List<Notice> = emptyList()

)