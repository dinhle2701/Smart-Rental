package com.project.SmartRental.hostel.service;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.hostel.dto.HostelReq;
import com.project.SmartRental.hostel.dto.HostelRes;
import com.project.SmartRental.hostel.model.Hostel;
import com.project.SmartRental.hostel.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class HostelServiceImpl implements HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    @Override
    public Page<HostelRes> getHostels(Pageable pageable) {
        return hostelRepository.findAll(pageable)
                .map(hostel -> HostelRes.builder()
                .id(hostel.getId())
                .hostelName(hostel.getHostelName())
                .hostelType(hostel.getHostelType())
                .address(hostel.getAddress())
                .availableRooms(hostel.getAvailableRooms())
                .description(hostel.getDescription())
                .district(hostel.getDistrict())
                .province(hostel.getProvince())
                .totalRooms(hostel.getTotalRooms())
                .latitude(hostel.getLatitude())
                .longtitude(hostel.getLongtitude())
                .createAt(hostel.getCreateAt())
                .updateAt(hostel.getUpdateAt())
                .build());
    }

    @Override
    public HostelRes createHostel(HostelReq hostelReq) {
        Hostel hostel = new Hostel();

        hostel.setHostelName(hostelReq.getHostelName());
        hostel.setHostelType(hostelReq.getHostelType());
        hostel.setAddress(hostelReq.getAddress());
        hostel.setAvailableRooms(hostelReq.getAvailableRooms());
        hostel.setDescription(hostelReq.getDescription());
        hostel.setDistrict(hostelReq.getDistrict());
        hostel.setProvince(hostelReq.getProvince());
        hostel.setTotalRooms(hostelReq.getTotalRooms());
        hostel.setLatitude(hostelReq.getLatitude());
        hostel.setLongtitude(hostelReq.getLongtitude());
        hostel.setCreateAt(LocalDateTime.now());

        Hostel created = hostelRepository.save(hostel);

        return HostelRes.builder()
                .id(created.getId())
                .hostelName(created.getHostelName())
                .hostelType(created.getHostelType())
                .address(created.getAddress())
                .availableRooms(created.getAvailableRooms())
                .description(created.getDescription())
                .district(created.getDistrict())
                .province(created.getProvince())
                .totalRooms(created.getTotalRooms())
                .latitude(created.getLatitude())
                .longtitude(created.getLongtitude())
                .createAt(created.getCreateAt())
                .updateAt(created.getUpdateAt())
                .build();
    }

    @Override
    public Optional<HostelRes> getById(Long id) {
        return hostelRepository.findById(id)
                .map(hostel -> HostelRes.builder()
                .id(hostel.getId())
                .hostelName(hostel.getHostelName())
                .hostelType(hostel.getHostelType())
                .address(hostel.getAddress())
                .availableRooms(hostel.getAvailableRooms())
                .description(hostel.getDescription())
                .district(hostel.getDistrict())
                .province(hostel.getProvince())
                .totalRooms(hostel.getTotalRooms())
                .latitude(hostel.getLatitude())
                .longtitude(hostel.getLongtitude())
                .createAt(hostel.getCreateAt())
                .updateAt(hostel.getUpdateAt())
                .build());
    }

    @Override
    public HostelRes updatedHostel(Long id, HostelReq hostelReq) {
        Hostel hostel = hostelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Hostel not found with id: " + id,
                        "/api/v1/hostel/" + id
                )
        );

        if (hostelReq.getHostelName() != null
                && !hostelReq.getHostelName().equals(hostel.getHostelName())) {
            hostel.setHostelName(hostelReq.getHostelName());
        }

        if (hostelReq.getHostelType() != null
                && !hostelReq.getHostelType().equals(hostel.getHostelType())) {
            hostel.setHostelType(hostelReq.getHostelType());
        }

        if (hostelReq.getDistrict() != null
                && !hostelReq.getDistrict().equals(hostel.getDistrict())) {
            hostel.setDistrict(hostelReq.getDistrict());
        }

        if (hostelReq.getDescription() != null
                && !hostelReq.getDescription().equals(hostel.getDescription())) {
            hostel.setDescription(hostelReq.getDescription());
        }

        if (hostelReq.getProvince() != null
                && !hostelReq.getProvince().equals(hostel.getProvince())) {
            hostel.setProvince(hostelReq.getProvince());
        }

        if (hostelReq.getAddress() != null
                && !hostelReq.getAddress().equals(hostel.getAddress())) {
            hostel.setAddress(hostelReq.getAddress());
        }

        if (hostelReq.getTotalRooms() != null
                && !hostelReq.getTotalRooms().equals(hostel.getTotalRooms())) {
            hostel.setTotalRooms(hostelReq.getTotalRooms());
        }

        if (hostelReq.getAvailableRooms() != null
                && !hostelReq.getHostelType().equals(hostel.getHostelType())) {
            hostel.setHostelType(hostelReq.getHostelType());
        }

        hostel.setUpdateAt(LocalDateTime.now());

        Hostel updated = hostelRepository.save(hostel);

        return HostelRes.builder()
                .id(updated.getId())
                .hostelName(updated.getHostelName())
                .hostelType(updated.getHostelType())
                .address(updated.getAddress())
                .availableRooms(updated.getAvailableRooms())
                .description(updated.getDescription())
                .district(updated.getDistrict())
                .province(updated.getProvince())
                .totalRooms(updated.getTotalRooms())
                .latitude(updated.getLatitude())
                .longtitude(updated.getLongtitude())
                .createAt(updated.getCreateAt())
                .updateAt(updated.getUpdateAt())
                .build();
    }

    @Override
    public HostelRes updatedPartialHostel(Long id, String field, HostelReq hostelReq) {
        Hostel hostel = hostelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Hostel not found with id: " + id,
                        "/api/v1/hostel/" + id
                )
        );

        switch (field){
            case "hostelName" -> hostel.setHostelName(hostel.getHostelName());
            case "hostelType" -> hostel.setHostelType(hostel.getHostelType());
            case "address" -> hostel.setAddress(hostel.getAddress());
            case "availableRooms" -> hostel.setAvailableRooms(hostel.getAvailableRooms());
            case "description" -> hostel.setDescription(hostel.getDescription());
            case "district" -> hostel.setDistrict(hostel.getDistrict());
            case "province" -> hostel.setProvince(hostel.getProvince());
            case "totalRooms" -> hostel.setTotalRooms(hostel.getTotalRooms());
            case "latitude" -> hostel.setLatitude(hostel.getLatitude());
            case "longtitude" -> hostel.setLongtitude(hostel.getLongtitude());
        }

        hostel.setUpdateAt(LocalDateTime.now());

        Hostel updatedPartial = hostelRepository.save(hostel);

        return HostelRes.builder()
                .id(updatedPartial.getId())
                .hostelName(updatedPartial.getHostelName())
                .hostelType(updatedPartial.getHostelType())
                .address(updatedPartial.getAddress())
                .availableRooms(updatedPartial.getAvailableRooms())
                .description(updatedPartial.getDescription())
                .district(updatedPartial.getDistrict())
                .province(updatedPartial.getProvince())
                .totalRooms(updatedPartial.getTotalRooms())
                .latitude(updatedPartial.getLatitude())
                .longtitude(updatedPartial.getLongtitude())
                .createAt(updatedPartial.getCreateAt())
                .updateAt(updatedPartial.getUpdateAt())
                .build();
    }

    @Override
    public void deleteHostel(Long id) {
        hostelRepository.deleteById(id);
    }
}
