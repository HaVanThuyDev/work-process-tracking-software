package com.example.vn2_ht_student.repository;

import com.example.vn2_ht_student.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByGmail(String gmail);
    boolean existsByGmail(String gmail);
    List<User> searchBy(String name, String email);


}
