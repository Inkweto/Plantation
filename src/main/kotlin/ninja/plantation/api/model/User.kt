package ninja.plantation.api.model

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.JoinColumn
import javax.persistence.CascadeType
import javax.persistence.OneToMany
import lombok.Data
import lombok.Builder
import lombok.NoArgsConstructor
import lombok.AllArgsConstructor


@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table
@org.springframework.data.relational.core.mapping.Table

public class User(
    
    @Column(name = "login", nullable = false, unique = true)
    var login: String = "",

    @Column(name = "passwordHash", nullable = false, unique = true)
    @JsonIgnore
    var password_hash: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = 0, 

    @OneToMany(mappedBy = "ownerId", cascade = arrayOf(CascadeType.ALL))
    @JsonIgnore
    var plants: List<Plant>? = mutableListOf(),
    
    @OneToMany(mappedBy = "userId", cascade = arrayOf(CascadeType.ALL))
    @JsonIgnore
    var notices: List<Notice>? = mutableListOf(),
)