package com.facebook.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facebook.community.model.Supporter;

        // Using Spring/JPA repository execute DML statements

public interface SupporterRepository extends JpaRepository<Supporter, String> {}
