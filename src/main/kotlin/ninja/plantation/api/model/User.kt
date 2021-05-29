package ninja.plantation.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.JoinColumn
import javax.persistence.CascadeType
import javax.persistence.OneToMany

@Entity
@Table(name = "user")
public class User(
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = 0, 

    @Column(name = "login", nullable = false, unique = true)
    var login: String = "",

    @Column(name = "password_hash", nullable = false, unique = true)
    var password_hash: String = "", 

    @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var plants: MutableList<Plant>?
    
)
// {
// 	override fun toString(): String{
//         return "User[id=${id}, login=${login}, password_hash=${password_hash}]"
// 	}
// }