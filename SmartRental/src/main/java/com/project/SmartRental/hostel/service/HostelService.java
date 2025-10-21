package com.project.SmartRental.hostel.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.hostel.dto.HostelReq;
import com.project.SmartRental.hostel.dto.HostelRes;

@Service
public interface HostelService {

    Page<HostelRes> getHostels(Pageable pageable);

    HostelRes createHostel(HostelReq hostelReq);

    Optional<HostelRes> getById(Long id);

    HostelRes updatedHostel(Long id, HostelReq hostelReq);

    HostelRes updatedPartialHostel(Long id, String field, HostelReq hostelReq);

    void deleteHostel(Long id);
}
