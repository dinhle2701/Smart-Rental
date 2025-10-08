package com.project.SmartRental.services.service;

import com.project.SmartRental.services.dto.res.ServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.services.dto.req.ServiceRequest;
import com.project.SmartRental.services.model.Services;
import com.project.SmartRental.vehicle.model.Vehicle;

import java.util.Optional;

@Service
public interface ServicesService {

    Page<ServiceResponse> getAllService(Pageable pageable);

    ServiceResponse saveService(ServiceRequest serviceRequest);

    Optional<ServiceResponse> getServiceById(Long id);

    Services updateService(Long id, ServiceRequest serviceRequest);

    void deleteService(Long id);
}
