package com.pnt.mobileshop.repository;

import com.pnt.mobileshop.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    List<User> findAll();

    User findUserById(Long id);

    void deleteById(Long id);


//    @Modifying
//    @Query(value = "select u.*, r.* from users u, roles r, users_roles ur " +
//            "where u.user_id = ur.user_id " +
//            "and r.role_id = ur.role_id ", nativeQuery = true)
//    List<Object> getAllUsersDetail();
}
