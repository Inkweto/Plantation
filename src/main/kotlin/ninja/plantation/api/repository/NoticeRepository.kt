package ninja.plantation.api.repository

import ninja.plantation.api.model.Notice
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.management.PlatformManagedObject

// CRUD functions for class Notice
@Repository
interface NoticeRepository: CrudRepository<PlatformManagedObject, Long> {
    //fun findByname(login: String): MutableList<Notice>
}