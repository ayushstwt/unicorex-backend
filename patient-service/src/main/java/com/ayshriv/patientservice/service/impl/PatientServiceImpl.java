package com.ayshriv.patientservice.service.impl;

import com.ayshriv.patientservice.common.CoreXStatus;
import com.ayshriv.patientservice.resources.dto.patient.CommonPatientRequest;
import com.ayshriv.patientservice.resources.dto.patient.PatientRequestDTO;
import com.ayshriv.patientservice.resources.dto.patient.PatientResponseDTO;
import com.ayshriv.patientservice.entity.Patient;
import com.ayshriv.patientservice.enums.Gender;
import com.ayshriv.patientservice.exception.EmailAlreadyExistsException;
import com.ayshriv.patientservice.exception.PhoneNumberAlreadyExistsException;
import com.ayshriv.patientservice.exception.ResourceNotFoundException;
import com.ayshriv.patientservice.mapper.PatientMapper;
import com.ayshriv.patientservice.repository.PatientRepository;
import com.ayshriv.patientservice.service.IPatientService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.ayshriv.patientservice.mapper.PatientMapper.mapToEntity;

@Service
public class PatientServiceImpl implements IPatientService {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        LOGGER.info("Start: Processing patient creation for details - {}", patientRequestDTO);
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + patientRequestDTO.getEmail());
        }
        if (patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException("A patient with this phone number already exists " + patientRequestDTO.getEmail());
        }
        Patient patient = mapToEntity(patientRequestDTO);
        PatientResponseDTO patientResponse=null;
        patient.setActive(true);
        patient.setDeleted(false);
        Patient savePatient = patientRepository.save(patient);
        if (savePatient != null) {
            patientResponse = PatientMapper.mapToDTO(savePatient);
            if (patientResponse != null) {
                LOGGER.info("Success: Patient created with ID - {}", patientResponse.getId());
            } else {
                LOGGER.warn("Warning: Patient creation failed for details - {}", patientRequestDTO);
            }
        }
        return patientResponse;
    }

    @Override
    public PatientResponseDTO updatePatient(Long id,PatientRequestDTO patientRequestDTO) {
        LOGGER.info("Start: Processing patient update for details - {}", patientRequestDTO);
        Patient foundPatient = patientRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id.toString()));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + patientRequestDTO.getEmail());
        }
        if (patientRepository.existsByPhoneNumber(patientRequestDTO.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException("A patient with this phone number already exists " + patientRequestDTO.getEmail());
        }
        foundPatient.setGender(Gender.valueOf(patientRequestDTO.getGender()));
        foundPatient.setFirstName(patientRequestDTO.getFirstName());
        foundPatient.setLastName(patientRequestDTO.getLastName());
        foundPatient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        foundPatient.setAddress(patientRequestDTO.getAddress());
        foundPatient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
        foundPatient.setEmail(patientRequestDTO.getEmail());
        Patient savePatient = patientRepository.save(foundPatient);
        PatientResponseDTO patientResponse = PatientMapper.mapToDTO(savePatient);
        if (patientResponse != null) {
            LOGGER.info("Success: Patient updated with ID - {}", patientResponse.getId());
        } else {
            LOGGER.warn("Warning: Patient updated failed for details - {}", patientRequestDTO);
        }
        return patientResponse;
    }

    @Override
    public PatientResponseDTO getPatient(Long id) {
        LOGGER.info("Start: Processing patient search for ID - {}", id);
        PatientResponseDTO patientResponse=null;
        Patient foundPatient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id.toString()));
        if (foundPatient != null) {
            patientResponse = PatientMapper.mapToDTO(foundPatient);
            if (patientResponse != null) {
                LOGGER.info("Success: Patient found with ID - {}", patientResponse.getId());
            } else {
                LOGGER.warn("Warning: Patient search failed for ID - {}", id);
            }
        }
        return patientResponse;
    }

    @Override
    public CoreXStatus getAllPatient(CommonPatientRequest request) {
        LOGGER.info("Start: Processing patient search for details - {}", request);
        Page<Patient> page = null;
        int pageNumber = 0, pageSize = 0;
        CoreXStatus coreXStatus = new CoreXStatus();
        pageNumber = (request.getPageNumber() >= 0) ? request.getPageNumber() : pageNumber;
        pageNumber = pageNumber - 1;
        pageSize = (request.getPageSize() >= 0) ? request.getPageSize() : pageSize;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (request.getOrderBy().equalsIgnoreCase("ASC")) {
            page = patientRepository.findAllASC(pageable);
        } else {
            page = patientRepository.findAllDESC(pageable);
        }
        List<Patient> patients = page.getContent();
        coreXStatus.setTotalPage(page.getTotalPages());
        coreXStatus.setTotalRecord(page.getTotalElements());
        coreXStatus.setData(patients.stream().map(patient -> PatientMapper.mapToDTO(patient)).collect(Collectors.toList()));
        return coreXStatus;
    }

    @Override
    public void deletePatient(Long patientId) {
        LOGGER.info("Start: Processing patient deletion for ID - {}", patientId);
        Patient foundPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId.toString()));
        foundPatient.setDeleted(true);
        Patient savePatient = patientRepository.save(foundPatient);
        if (savePatient != null) {
            LOGGER.info("Success: Patient deleted with ID - {}", patientId);
        } else {
            LOGGER.warn("Warning: Patient deletion failed for ID - {}", patientId);
        }
    }
}
