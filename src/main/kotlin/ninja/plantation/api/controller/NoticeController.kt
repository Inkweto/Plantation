
package ninja.plantation.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.http.ResponseEntity

import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.NoticeRepository
import ninja.plantation.api.services.NoticeService

@RestController
@CrossOrigin(origins = ["http://localhost:8000", "http://localhost:10533"])
@RequestMapping("/notices")
class NoticeController(private val noticeService: NoticeService) {
	
    @Autowired
    lateinit var repository: NoticeRepository
       
    @PostMapping("")
    fun process(@CookieValue("jwt") jwt: String?, @RequestBody newNotice : Notice): ResponseEntity<Any> {
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));         
            return ResponseEntity.ok(noticeService.addNotice(newNotice));
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }
    
    @GetMapping("")
    fun findAll(@CookieValue("jwt") jwt: String?): ResponseEntity<Any>{  
        try {
            if(jwt == null)
                return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));         
            return ResponseEntity.ok(noticeService.getNotices());
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(MsgResponse("Unauthenticated user"));
        }
    }
       
    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long): Notice{
        return repository.findById(id).orElse(null)
    }

    data class MsgResponse(val msg: String?)
}
