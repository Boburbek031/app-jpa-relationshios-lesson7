package uz.pdp.appjparelationshioslesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(optional = false) // optional ni manosi address qo'yishi majburiy deyapmiz:
    private Address address;

    public University(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
