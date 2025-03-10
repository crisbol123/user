package com.pragma.user.adapters.driven.jpa.mysql.repository;


import com.pragma.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository  extends JpaRepository<RoleEntity, String>
{
    RoleEntity findByName(String name);
}
