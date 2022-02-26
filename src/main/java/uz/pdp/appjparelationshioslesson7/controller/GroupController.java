package uz.pdp.appjparelationshioslesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshioslesson7.entity.Faculty;
import uz.pdp.appjparelationshioslesson7.entity.Groups;
import uz.pdp.appjparelationshioslesson7.payload.GroupDto;
import uz.pdp.appjparelationshioslesson7.repository.FacultyRepository;
import uz.pdp.appjparelationshioslesson7.repository.GroupsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {


    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    FacultyRepository facultyRepository;

    // READ VAZIRLIK UCHUN:
    @GetMapping("/all")
    public List<Groups> getAllGroups() {
        return groupsRepository.findAll();
    }


    // Mas'ul institutdagi odam uchun:
    @GetMapping("/byUniId/{uniId}")
    public List<Groups> getGroupsByUniversityId(@PathVariable Long uniId) {
        return groupsRepository.findAllByFaculty_University_Id(uniId);
    }


    @PostMapping("/add")
    public String addGroup(@RequestBody GroupDto groupDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return "Faculty cannot be found!";
        }
        Groups group = new Groups();
        group.setName(groupDto.getName());
        Faculty faculty = optionalFaculty.get();
        group.setFaculty(faculty); // Guruh bilan faculty ni unique qilib ket keyin
        groupsRepository.save(group);
        return "Group Added!";
    }


    @PutMapping("/update/{id}")
    public String updateGroup(@PathVariable Long id, @RequestBody GroupDto dto) {
        Optional<Groups> optionalGroup = groupsRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Groups group = optionalGroup.get();
            Optional<Faculty> optionalFaculty = facultyRepository.findById(dto.getFacultyId());
            if (!optionalFaculty.isPresent()) {
                return "Faculty Not Found!";
            }
            group.setName(dto.getName());
            group.setFaculty(optionalFaculty.get());
            groupsRepository.save(group);
            return "Group Updated";
        }
        return "Group Not Found!";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteGroup(@PathVariable Long id) {
        try {
            groupsRepository.deleteById(id);
            return "Deleted!";
        } catch (Exception e) {
            return "Error on Deleting!";
        }
    }
}
