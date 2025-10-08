package com.project.SmartRental.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SmartRental.vehicle.model.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
//    Page<Vehicle> findAll(Pageable pageable);
//    Iterable<Vehicle> findAll(Sort sort);
}
