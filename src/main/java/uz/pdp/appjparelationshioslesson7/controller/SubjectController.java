package uz.pdp.appjparelationshioslesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshioslesson7.entity.Subject;
import uz.pdp.appjparelationshioslesson7.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;


    @GetMapping("/all")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }


    /*    @PostMapping("/add")
    public String addSubject (@RequestBody Subject subject){
       try {
           subjectRepository.save(subject);
           return "Subject added!";
       } catch (Exception e){
           return "There is the same subject with this name: " + subject.getName() + ". Please enter different subject!";
       }
    }*/ // Mine add method

    @PostMapping("/add")
    public String addSubject(@RequestBody Subject subject) {
        // Subject bazada bormi yoki yo'qmi shuni tekshirvolishimiz kerak:
        // Uning uchun biz JPA query yozamiz. SubjectRepository ni ichida
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName) {
            return "This subject already exists";
        }
        subjectRepository.save(subject);
        return "New Subject added!";
    }


    @PutMapping("/update/{id}")
    public String updateSub(@PathVariable Long id, @RequestBody Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject1 = optionalSubject.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return "Subject Updated!";
        }
        return "Subject cannot be updated!";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteSub(@PathVariable Long id) {
        try {
            subjectRepository.deleteById(id);
            return "Deleted!";
        } catch (Exception e) {
            return "Cannot be found and Deleted!";
        }
    }


}
