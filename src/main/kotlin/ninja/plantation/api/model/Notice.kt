package ninja.plantation.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
public class Notice(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id", nullable = false, updatable = false)
    var notice_id: Long = 0, 

    @Column(name = "user_id")
    var user_id: Long? = null,

    @Column(name = "plant")
    var plant_id: Long? = null,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "notice_text")
    var notice_text: String? = null


)