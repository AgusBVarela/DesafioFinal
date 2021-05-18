package com.mercadolibre.varela_agustina.repository;

import com.mercadolibre.varela_agustina.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    @Query(value=" INSERT INTO user " +
            " (id,address,country,password,phone,role,username,id_dealer) " +
            " VALUES" +
            " ( :userId , :address , :country , :password , :phone , :role , :username , :id_dealer); ",nativeQuery = true)
    @Modifying
    @Transactional
    void insertUser(@Param("userId") Long userId, @Param("address") String address, @Param("country") String country,
                    @Param("password") String password, @Param("phone") String phone, @Param("role") String role, @Param("username") String username,
                    @Param("id_dealer") Long id_dealer);
}
