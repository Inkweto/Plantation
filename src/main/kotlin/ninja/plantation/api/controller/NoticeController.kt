
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

import ninja.plantation.api.model.Notice
import ninja.plantation.api.repository.NoticeRepository

@RestController
@RequestMapping("/notices")
class NoticeController {
	
    @Autowired
    lateinit var repository: NoticeRepository
       
    @PostMapping("")
    fun process(@RequestBody newNotice : Notice): String{
        return repository.save(newNotice).toString()
    }
       
       
    @GetMapping("")
    fun findAll(): Iterable<Notice>{           
        return repository.findAll()
    }
       
    @GetMapping("/{id}")
    fun findById(@PathVariable id : Long): Notice{
        return repository.findById(id).orElse(null)
    }
}
