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
import lombok.Data;

@Entity
@Data
@Table
@org.springframework.data.relational.core.mapping.Table

public class Plant(
    
    @Column(name = "name")
    var name: String = "",

    @ManyToOne 
    @JoinColumn(name="ownerId")
    val ownerId: User? = null,

    @Column(name = "heigth")
    var heigth: Long? = 0,

    @Column(name = "diameter")
    var diameter: Long? = 0,

    @Column(name = "photoPath")
    var photoPath: String? = null,

    @OneToMany(mappedBy = "plantId", cascade = arrayOf(CascadeType.ALL))
    var notices: List<Notice>? = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var plantId: Long = 0
)