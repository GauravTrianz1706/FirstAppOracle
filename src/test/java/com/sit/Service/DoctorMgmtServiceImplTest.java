package com.sit.Service;

import com.sit.Repository.IDoctorRepo;
import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DoctorMgmtServiceImpl.
 * Uses Mockito to mock IDoctorRepo dependency.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("DoctorMgmtServiceImpl Tests")
class DoctorMgmtServiceImplTest {

    @Mock
    private IDoctorRepo doctorRepo;

    @InjectMocks
    private DoctorMgmtServiceImpl doctorMgmtService;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setDocId(203);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);
    }

    // ─── registerDoctor Tests ────────────────────────────────────────────────

    @Test
    @DisplayName("registerDoctor returns success message with doctor ID")
    void testRegisterDoctor_withValidDoctor_returnsSuccessMessage() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :203", result);
    }

    @Test
    @DisplayName("registerDoctor calls repository save exactly once")
    void testRegisterDoctor_callsRepoSaveOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor returns message containing doctor ID")
    void testRegisterDoctor_returnMessageContainsDoctorId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.contains("203"));
    }

    @Test
    @DisplayName("registerDoctor returns message with correct prefix")
    void testRegisterDoctor_returnMessageHasCorrectPrefix() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertTrue(result.startsWith("Doctor obj is saved with id value :"));
    }

    @Test
    @DisplayName("registerDoctor with different doctor ID returns correct message")
    void testRegisterDoctor_withDifferentDoctorId_returnsCorrectMessage() {
        // Arrange
        Doctor anotherDoctor = new Doctor();
        anotherDoctor.setDocId(999);
        anotherDoctor.setDocName("John");
        anotherDoctor.setSpecialization("Neurology");
        anotherDoctor.setIncome(120000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(anotherDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(anotherDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :999", result);
    }

    @Test
    @DisplayName("registerDoctor passes the correct doctor object to repository")
    void testRegisterDoctor_passesCorrectDoctorToRepo() {
        // Arrange
        when(doctorRepo.save(doctor)).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verify(doctorRepo).save(doctor);
    }

    @Test
    @DisplayName("registerDoctor with doctor having null ID - repo returns doctor with generated ID")
    void testRegisterDoctor_withNullId_repoReturnsGeneratedId() {
        // Arrange
        Doctor inputDoctor = new Doctor();
        inputDoctor.setDocName("newDoctor");
        inputDoctor.setSpecialization("Orthopedics");
        inputDoctor.setIncome(75000.00);

        Doctor savedDoctor = new Doctor();
        savedDoctor.setDocId(204);
        savedDoctor.setDocName("newDoctor");
        savedDoctor.setSpecialization("Orthopedics");
        savedDoctor.setIncome(75000.00);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(savedDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(inputDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :204", result);
    }

    @Test
    @DisplayName("registerDoctor result is not empty")
    void testRegisterDoctor_resultIsNotEmpty() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        String result = doctorMgmtService.registerDoctor(doctor);

        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("registerDoctor - service implements IDoctorService interface")
    void testDoctorMgmtServiceImpl_implementsIDoctorService() {
        // Assert
        assertTrue(doctorMgmtService instanceof IDoctorService);
    }

    @Test
    @DisplayName("registerDoctor - no extra interactions with repository")
    void testRegisterDoctor_noExtraRepoInteractions() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorMgmtService.registerDoctor(doctor);

        // Assert
        verifyNoMoreInteractions(doctorRepo);
    }

    @Test
    @DisplayName("registerDoctor with doctor having zero income")
    void testRegisterDoctor_withZeroIncome() {
        // Arrange
        Doctor zeroIncomeDoctor = new Doctor();
        zeroIncomeDoctor.setDocId(205);
        zeroIncomeDoctor.setDocName("TestDoc");
        zeroIncomeDoctor.setSpecialization("General");
        zeroIncomeDoctor.setIncome(0.0);

        when(doctorRepo.save(any(Doctor.class))).thenReturn(zeroIncomeDoctor);

        // Act
        String result = doctorMgmtService.registerDoctor(zeroIncomeDoctor);

        // Assert
        assertEquals("Doctor obj is saved with id value :205", result);
    }
}
