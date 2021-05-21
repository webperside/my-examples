package com.webperside.authservice.repository;

import com.webperside.authservice.models.UserAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, Integer> {

    Optional<UserAuthorization> findByUserId(Integer userId);

    Optional<UserAuthorization> findByRefreshToken(String refreshToken);

}
