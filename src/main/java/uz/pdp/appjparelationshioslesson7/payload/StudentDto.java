package uz.pdp.appjparelationshioslesson7.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {


    private Long id;
    private String firstName;
    private String lastName;
    private Long addressId;
    private Long groupId;

    public StudentDto(String firstName, String lastName, Long addressId, Long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.groupId = groupId;
    }
}
