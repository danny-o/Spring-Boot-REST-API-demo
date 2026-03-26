package com.digitalskies.demo.login;


import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {

    List<RefreshToken> findByUserId(String userId);
}
