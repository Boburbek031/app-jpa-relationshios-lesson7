package uz.pdp.appjparelationshioslesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationshioslesson7.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByNameAndUniversityId(String name, Long university_id);

    // Shunga teng: Select * from faculty where university_id = id;
    List<Faculty> findAllByUniversityId(Long university_id); // SHu Jpa Query hissoblanadi:

}
