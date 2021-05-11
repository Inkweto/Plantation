package ninja.plantation.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class User(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?, 

    @Column(name = "login")
    var login: String,

    @Column(name = "password_hash")
 
    var password_hash: String
)
{
	override fun toString(): String{
        return "User[id=${id}, login=${login}, password_hash=${password_hash}]"
	}
}