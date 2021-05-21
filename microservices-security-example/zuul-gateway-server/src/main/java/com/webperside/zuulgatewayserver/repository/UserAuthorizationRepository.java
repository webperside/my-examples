package com.webperside.zuulgatewayserver.repository;

import com.webperside.zuulgatewayserver.models.UserAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, Integer> {

    Optional<UserAuthorization> findByUserId(Integer userId);

    boolean existsByUserIdAndAccessToken(Integer userId, String accessToken);

}
