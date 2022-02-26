package uz.pdp.appjparelationshioslesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationshioslesson7.entity.Address;
import uz.pdp.appjparelationshioslesson7.entity.University;
import uz.pdp.appjparelationshioslesson7.payload.UniversityDto;
import uz.pdp.appjparelationshioslesson7.repository.AddressRepository;
import uz.pdp.appjparelationshioslesson7.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uni")
public class UniversityController {


    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    // READ ALL UNIS
    @GetMapping("/all")
    public List<University> getAllUnis() {
        return universityRepository.findAll();
    }


    // CREATE OR ADD UNI
    @PostMapping("/add")
    public String addUni(@RequestBody UniversityDto uniDto) { // /uni/add yo'liga  post methodi bilan (json) malumot jo'natganni @RequestBody
        try {
            // Bilan UniversityDto classiga parse qilib ber deyapmiz:
            Address address = new Address(uniDto.getCity(), uniDto.getDistrict(), uniDto.getStreet()); // bu bazaga saqlanadigan table
            Address savedAddress = addressRepository.save(address);// Address ni save qildik: Endi Universitetni ham saqlaymiz.

            // YANGI UNI YARATIB OLDIK
            University university = new University(uniDto.getName(), savedAddress);
            // DB GA NEW UNI NI SAVE QILDIK:
            universityRepository.save(university);
            return "New Uni Added!";
        } catch (Exception e) {
            return "Cannot be added";
        }
    }


    // UPDATE THE UNI
    @PutMapping("/update/{id}")
    public String updateUni(@PathVariable Long id, @RequestBody UniversityDto uniDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            university.setName(uniDto.getName());
            // Uni ni db dan kegan o'zini eski adresi, chunki biz uni object ini chaqirsak, avtomat ravishda
            // JPA uni ga tegishli bo'lgan field larni hammasini chaqiradi: U field larni ichida address ham boridi biz uni object qilib ovoldik
            Address address = university.getAddress();
            // YANGI QIYMATLARNI SET QILDIK:
            address.setCity(uniDto.getCity());
            address.setDistrict(uniDto.getDistrict());
            address.setStreet(uniDto.getStreet());

            // ADDRESS NI SAVE QIVOLDIK
            addressRepository.save(address);
            // ENDI UNI HAM YANGI O'ZGARISHLARI BILAN SAVE QIDIK
            universityRepository.save(university);
            return "University Updated!";
        }
        return "Not found";
    }


    // DELETE THE UNI
    @DeleteMapping("/delete/{id}")
    public String deleteUni(@PathVariable Long id) {
        try {
            universityRepository.deleteById(id);
            return "Deleted!";
        } catch (Exception e) {
            return "Cannot be Deleted!";
        }
    }


}
