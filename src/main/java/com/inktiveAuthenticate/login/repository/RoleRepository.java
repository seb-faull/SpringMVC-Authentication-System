package com.inktiveAuthenticate.login.repository;

import com.inktiveAuthenticate.login.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleType(String roleType);
}
