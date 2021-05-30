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
    @Column(name = "noticeId", nullable = false, updatable = false)
    var noticeId: Long = 0, 

    @ManyToOne
    @JoinColumn(name="userId")
    val userId: User? = null,

    @ManyToOne
    @JoinColumn(name="plantId")
    val plantId: Plant? = null,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "noticeText")
    var noticeText: String? = null

)