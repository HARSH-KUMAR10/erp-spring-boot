package com.harshkumar093.erp.repository;

import com.harshkumar093.erp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {}