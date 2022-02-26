package uz.pdp.appjparelationshioslesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    // Bir class ni ichida boshqa bir class ni isshlatishimiz aggregation deyiladi:
    // Huddi shu narsa Database ga foreign key bo'lib o'tadi, yani ikkita table ni bir biri bilan bog'laydi:
    // Buning uchun biz annotatsiya lardan foydalanamiz (OneToOne, ....).

    // Bu holatda hozir bitta address bitta studentga tegishli bo'lyapti
    @OneToOne  // ONE student TO ONE address  ***  ONE address TO ONE student
    private Address address;

    @ManyToOne // Ko'plab studentlarga bitta guruh
    private Groups group;


    @ManyToMany  // 1 ta student ni ko'plab fani bo'ladi, 1 ta fanning ko'plab syudentlari bo'ladi degan manoda:
    private List<Subject> subjects;

}
