package ninja.plantation.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import lombok.Data;

@Entity
@Data
@Table
@org.springframework.data.relational.core.mapping.Table

public class Notice(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", nullable = false, updatable = false)
    var notice_id: Long = 0, 

    @ManyToOne 
    val user_id: User? = null,

    @ManyToOne
    val plant_id: Plant? = null,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "notice_text")
    var notice_text: String? = null

)