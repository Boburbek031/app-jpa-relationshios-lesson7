package uz.pdp.appjparelationshioslesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationshioslesson7.entity.Subject;


public interface SubjectRepository extends JpaRepository<Subject, Long> {

    // shu yerda abstract methodlarni yozamiz:
    // shu method = select count(*) from subject where name = 'name'; 0 qaytarsa yo'q bo'ladi, 0 dan katta qaytarsa bor bo'ladi:
   // shu methodimizni Jpa query ga o'girib beradi:
    boolean existsByName(String name);

}
