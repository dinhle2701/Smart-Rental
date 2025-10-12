package com.project.SmartRental.general.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SmartRental.general.services.model.Services;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {

}
