package uz.pdp.appjparelationshioslesson7.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationshioslesson7.entity.Student;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {

    // _ shu belgi join qiberadi: Tagida shu query yotipdi:
//    Select * from student s
//    join groups g on s.group_id = g.id
//    join faculty f on g.faculty_id = f.id
//    join university u on f.university_id = u.id
//    where u.id =1 limit 2 offset 0;
    Page<Student> findAllByGroup_Faculty_University_Id(Long group_faculty_university_id, Pageable pageable);


    Page<Student> findAllByGroup_FacultyId(Long group_faculty_id, Pageable pageable);


    Page<Student> findAllByGroupId(Long group_id, Pageable pageable);
}
