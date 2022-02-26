package uz.pdp.appjparelationshioslesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // ikkita column birga keganda unique qilib qo'yyapmiz, MS: A degan Uni da matem faculty bor, va huddi shu Unida yana
// matem faculty bo'lolmedi, lekin B unida Matem degan faculty bo'la oladi"
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "university_id"})) // database dagi nomni berishimiz kerak, university_id misolida.
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private University university;

}
