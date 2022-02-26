package uz.pdp.appjparelationshioslesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshioslesson7.entity.Faculty;
import uz.pdp.appjparelationshioslesson7.entity.University;
import uz.pdp.appjparelationshioslesson7.payload.FacultyDto;
import uz.pdp.appjparelationshioslesson7.repository.FacultyRepository;
import uz.pdp.appjparelationshioslesson7.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {


    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    // VAZIRLIK UCHUN HAMMA FACULTETLARNI KO'RA OLADI
    @GetMapping("/all")
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }


    @PostMapping("/add")
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists) {
            return "The university and Faculty already exists!";
        }
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());

        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            faculty.setUniversity(university);
            facultyRepository.save(faculty);
            return "New Faculty Added!";
        }
        return "University cannot be Found!";
    }


    // UNIVERSITET HODIMI UCHUN:
    @GetMapping("/byUniId/{uniId}")
    public List<Faculty> getFacultyByUniId(@PathVariable Long uniId) {
        return facultyRepository.findAllByUniversityId(uniId);
    }


    @PutMapping("/update/{id}")
    public String updateFaculty(@PathVariable Long id, @RequestBody FacultyDto dto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Optional<University> optionalUniversity = universityRepository.findById(dto.getUniversityId());
            if (!optionalFaculty.isPresent()) {
                return "University not found!";
            }
            Faculty faculty = optionalFaculty.get();
            faculty.setName(dto.getName());
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty Updated!";
        }
        return "Faculty not found!";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted!";
        } catch (Exception e) {
            return "Error!";
        }
    }


}
