
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod

import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.NoticeRepository

@RestController
class NoticeController {
	
    @Autowired
    lateinit var repository: NoticeRepository
       
    @RequestMapping("/saveNotice", method = arrayOf(RequestMethod.POST))
    fun process(): String{
        return "Done"
    }
       
       
    @RequestMapping("/findAllNotices")
    fun findAll(): String{
        var result = ""
		
        for(cust in repository.findAll()){
            result += cust.toString() + ""
        }
           
        return result
    }
       
    @RequestMapping("/findNoticeById")
    fun findById(@RequestParam("notice_id") notice_id: Long): String{
        return repository.findById(notice_id).orElse(null).toString()
    }
}
