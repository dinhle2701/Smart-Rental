package com.project.SmartRental.services.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.services.dto.req.ServiceRequest;
import com.project.SmartRental.services.dto.res.ServiceResponse;
import com.project.SmartRental.services.model.Services;
import com.project.SmartRental.services.repository.ServiceRepository;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<ServiceResponse> getAllService(Pageable pageable) {
        try {
            return serviceRepository.findAll(pageable)
                    .map(service -> ServiceResponse.builder()
                    .id(service.getId())
                    .serviceName(service.getServiceName())
                    .serviceType(service.getServiceType())
                    .build()
                    );
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage(), e.getLocalizedMessage());
        }
    }

    @Override
    public ServiceResponse saveService(ServiceRequest serviceRequest) {
        Services service = new Services();
        service.setServiceName(serviceRequest.getServiceName());
        service.setServiceType(serviceRequest.getServiceType());
        Services saved = serviceRepository.save(service);

        return ServiceResponse.builder()
                .id(saved.getId())
                .serviceName(saved.getServiceName())
                .serviceType(saved.getServiceType())
                .build();
    }

    @Override
    public Optional<ServiceResponse> getServiceById(Long id) {
        var service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Service not found with id: " + id,
                "/api/services/" + id
        ));
        return serviceRepository.findById(id)
                .map(services -> ServiceResponse.builder()
                .id(services.getId())
                .serviceName(services.getServiceName())
                .serviceName(services.getServiceName())
                .serviceType(services.getServiceType())
                .build());
    }

    @Override
    public ServiceResponse updateService(Long id, ServiceRequest serviceRequest) {
        Services services = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Service not found with id: " + id,
                "/api/services/" + id
        ));

        boolean isUpdated = false;

        if (serviceRequest.getServiceName() != null
                && !serviceRequest.getServiceName().equals(services.getServiceName())) {
            services.setServiceName(serviceRequest.getServiceName());
            isUpdated = true;
        }

        if (serviceRequest.getServiceType() != null
                && !serviceRequest.getServiceType().equals(services.getServiceType())) {
            services.setServiceType(serviceRequest.getServiceType());
            isUpdated = true;
        }

        if (isUpdated) {
            services = serviceRepository.save(services);
        }

//        services.setServiceName(serviceRequest.getServiceName());
//        services.setServiceType(serviceRequest.getServiceType());
//        Services updated = serviceRepository.save(services);
        if (isUpdated) {
            services = serviceRepository.save(services);
        }

        return ServiceResponse.builder()
                .id(services.getId())
                .serviceName(services.getServiceName())
                .serviceType(services.getServiceType())
                .build();
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

}
