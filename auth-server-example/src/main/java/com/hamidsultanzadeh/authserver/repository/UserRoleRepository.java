package com.hamidsultanzadeh.authserver.repository;

import com.hamidsultanzadeh.authserver.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
