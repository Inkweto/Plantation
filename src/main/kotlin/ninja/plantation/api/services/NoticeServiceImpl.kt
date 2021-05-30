package ninja.plantation.api.services

import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.NoticeRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class NoticeServiceImpl: NoticeService {

    private val log = KotlinLogging.logger {}
    @Autowired
    lateinit var repository: NoticeRepository

    override fun getNotices() =
            repository.findAll()

     override fun getNoticeById(notice_id: Long) =
             repository.findById(notice_id).orElse(null);


    override fun addNotice(this_notice: Notice): Notice {
        repository.save(this_notice)
        log.info("${this_notice.name},added!!!")
        return this_notice
    }

    override fun deleteNotice(notice_id: Long) {
        repository.deleteById(notice_id)
        log.info("Notice deleted!!")
    }

    override fun deleteAllNotices() {
        repository.deleteAll()
        log.info("All Notices deleted!!")
    }

    override fun updateNotice(notice_id: Long, this_notice: Notice):Notice {
        val currentNotice = repository.findById(notice_id).orElse(null);
         if (currentNotice != null) 
            repository.save(currentNotice)
        log.info("${this_notice.name},updated!!!")
      return currentNotice

    }

}
