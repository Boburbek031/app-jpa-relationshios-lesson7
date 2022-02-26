package uz.pdp.appjparelationshioslesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appjparelationshioslesson7.entity.Address;

@Repository // Bu annotatsiyani qo'ysak ham qo'ymasak ham o'zi bean qilib beradi, chunki biz JpaRepository dan extends qilganmiz:
public interface AddressRepository extends JpaRepository<Address, Long> {

}
