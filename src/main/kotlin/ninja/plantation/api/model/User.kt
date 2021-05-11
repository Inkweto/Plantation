package ninja.plantation.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
public class User(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null, 

    @Column(name = "login")
    var login: String? = null,

    @Column(name = "password_hash")
 
    var password_hash: String? = null
    
)


// {
// 	override fun toString(): String{
//         return "User[id=${id}, login=${login}, password_hash=${password_hash}]"
// 	}
// }