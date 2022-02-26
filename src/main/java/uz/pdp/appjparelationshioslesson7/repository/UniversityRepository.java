package uz.pdp.appjparelationshioslesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appjparelationshioslesson7.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

}
