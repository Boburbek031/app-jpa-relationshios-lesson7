package uz.pdp.appjparelationshioslesson7.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor  // D: DATA   T: TRANSFER  O: OBJECT
public class UniversityDto { // Bu class ni vazifasi: Client tomonidan kelgan ma'lumotni bizni methodimizni ichiga
                            // parse qilib beruvchi class: Malumot tashuvchi class


    private String name;
    private String city;
    private String district;
    private String street;




}
