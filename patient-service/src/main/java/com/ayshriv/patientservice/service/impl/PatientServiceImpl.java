package com.ayshriv.patientservice.service.impl;

import com.ayshriv.patientservice.dto.patient.PatientRequestDTO;
import com.ayshriv.patientservice.dto.patient.PatientResponseDTO;
import com.ayshriv.patientservice.entity.Patient;
import com.ayshriv.patientservice.mapper.PatientMapper;
import com.ayshriv.patientservice.repository.PatientRepository;
import com.ayshriv.patientservice.service.IPatientService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ayshriv.patientservice.mapper.PatientMapper.mapToEntity;

@Service
public class PatientServiceImpl implements IPatientService {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        LOGGER.info("Start: Processing patient creation for details - {}", patientRequestDTO);
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
    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO) {
        LOGGER.info("Start: Processing patient creation for details - {}", patientRequestDTO);
        PatientResponseDTO patientResponse = PatientMapper.mapToDTO(patientRepository.save(mapToEntity(patientRequestDTO)));
        if (patientResponse != null) {
            LOGGER.info("Success: Patient created with ID - {}", patientResponse.getId());
        } else {
            LOGGER.warn("Warning: Patient creation failed for details - {}", patientRequestDTO);
        }
        return patientResponse;
    }

    @Override
    public PatientResponseDTO getPatient(PatientRequestDTO patientRequestDTO) {
        return null;
    }

    @Override
    public List<PatientResponseDTO> getAllPatient(PatientRequestDTO patientRequestDTO) {
        return List.of();
    }

    @Override
    public boolean deletePatient(Long patientId) {
        return false;
    }
}
