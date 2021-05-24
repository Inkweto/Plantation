package ninja.plantation.api.services

import ninja.plantation.api.model.Notice

interface NoticeService {


    fun addNotice(this_notice: Notice): Notice
    fun deleteNotice(notice_id: Long)
    fun deleteAllNotices()
    fun getNoticeById(notice_id: Long): Notice?
    fun getNotices(): MutableIterable<Notice>?
    //fun getNoticeByUserId(user_id: Long): List<Notice>
    fun updateNotice(notice_id: Long, this_notice: Notice): Notice
}