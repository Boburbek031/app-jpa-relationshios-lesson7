package uz.pdp.appjparelationshioslesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshioslesson7.entity.Student;
import uz.pdp.appjparelationshioslesson7.repository.StudentRepo;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;


    // 1. Vazirlik
    @GetMapping("/forMinistry")// client nechanchi page daligini bervoradi, bizga.
    public Page<Student> getStudentsForMinistry(@RequestParam int page) {
        // Tagida shu query yotibdi:
        // 1-1 = 0
        // 2-1 = 1 // 2 - sahifani ko'rmoqchi
        // 3-1 = 2
        // 4-1 = 3 front-end dan bizda 4 keladi, biz 1 - ayirvolamiz
        // select * from student limit 10 offset 0 ==> 0 - sahifada turgananimiz uchun 0 deyapmiz: va boshidan olib keladi
        // select * from student limit 10 offset (1*10) ==>  10 tashlob undan keyingi 10 tani olib keladi.
        // select * from student limit 10 offset (2*10) ==> 20 tashlob undan keyingi 10 tani olib keladi. shunda 3 - sahifadamiz
        // select * from student limit 10 offset (3*10) ==> 30 tashlob undan keyingi 10 tani olib keladi. shunda 4 - sahifadamiz
        // Bu delani 4 hi on'talikni endi ko'rishni boshladik degani.
        // Pageable bu interface
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepo.findAll(pageable);
        return studentPage;
    }


    // 2. University
    @GetMapping("/forUniversity/{uniId}")
    public Page<Student> getStudentsForUni(@PathVariable Long uniId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepo.findAllByGroup_Faculty_University_Id(uniId, pageable);
        return studentPage;
    }



}
