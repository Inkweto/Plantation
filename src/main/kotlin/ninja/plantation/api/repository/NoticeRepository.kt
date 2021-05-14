package ninja.plantation.api.repository

import ninja.plantation.api.model.Notice
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.management.PlatformManagedObject


@Repository
interface NoticeRepository: CrudRepository<Notice, Long> {
   // fun findByLogin(login: String): MutableList<User>
}