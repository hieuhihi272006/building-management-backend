package com.javaweb.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.api.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
}
