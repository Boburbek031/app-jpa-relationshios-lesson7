package uz.pdp.appjparelationshioslesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appjparelationshioslesson7.entity.Groups;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

    // Bu JPA query orqali: Bu query ni ham tagida join lar yotipdi: Shunqaga guruhlarni oberki, qaysiki fakulteti,
    // deganimizda fakultetga o'tvoldi, fakultetni ichida university bor va uni id si shu uni id ga teng bo'ganini deyapmiz    //
    List<Groups> findAllByFaculty_University_Id(Long faculty_university_id);
//    List<Groups> findAllByFaculty_UniversityId(Long faculty_university_id); // bunaqa yozzak ham bo'ladi:


    // NATIVE BO'LMAGAN QUERY:
    @Query("select gr from Groups gr where gr.faculty.university.id=:universityId")
    List<Groups> getGroupsByUniversityIdNonNative(Long universityId);


    @Query(value = "select * from groups g join faculty f on g.faculty_id = f.id" +
            "join university u on u.id = f.university_idselect *\n" +
            "from groups g\n" +
            "         join faculty f on g.faculty_id = f.id\n" +
            "         join university u\n" +
            "              on u.id = f.university_id\n" +
            "where u.id = : universityId", nativeQuery = true)
    List<Groups> getGroupsByUniversityIdNative(Long universityId);


}
