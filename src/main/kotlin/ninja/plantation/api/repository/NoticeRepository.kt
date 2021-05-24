package ninja.plantation.api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.lang.management.PlatformManagedObject

import ninja.plantation.api.model.Notice

@Repository
interface NoticeRepository: CrudRepository<Notice, Long> {
    //fun findByUserId(user_id: Long): MutableList<Notice>
}