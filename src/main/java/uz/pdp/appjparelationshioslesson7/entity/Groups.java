package uz.pdp.appjparelationshioslesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ManyToOne // MANY groups TO ONE faculty
    private Faculty faculty;


  /*  @OneToMany // ONE group ga TO MANY students (many bo'gani uchun studentlarni listi bo'ladi:) //  Students Id unique bo'ladi
    private List<Student> students;  // Aslida bu yerda listni o'rniga Set ishlatishimiz kerak bo'ladi:
*/
}
