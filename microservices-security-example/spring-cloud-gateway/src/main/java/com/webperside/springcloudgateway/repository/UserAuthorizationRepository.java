package com.webperside.springcloudgateway.repository;

import com.webperside.springcloudgateway.models.UserAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, Integer> {

    Optional<UserAuthorization> findByUserId(Integer userId);

    boolean existsByUserIdAndAccessToken(Integer userId, String accessToken);

}
