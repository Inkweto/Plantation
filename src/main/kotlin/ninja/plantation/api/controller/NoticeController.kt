
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.NoticeRepository

@RestController
class NoticeController {
	
    @Autowired
    lateinit var repository: NoticeRepository
       
    @RequestMapping("/saveNotice")
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
    fun findById(@RequestParam("id") id: Long): String{
        return repository.findById(id).orElse(null).toString()
    }
}
