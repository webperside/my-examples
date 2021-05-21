package com.hamidsultanzadeh.authserver.repository;

import com.hamidsultanzadeh.authserver.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

    List<UserLogin> findAllByUserId(Integer userId);
}
