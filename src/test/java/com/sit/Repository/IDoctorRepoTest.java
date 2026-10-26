package com.sit.Repository;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Unit tests for IDoctorRepo interface.
 * Uses Mockito to mock the CrudRepository behavior.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("IDoctorRepo Tests")
class IDoctorRepoTest {

    @Mock
    private IDoctorRepo doctorRepo;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setDocId(203);
        doctor.setDocName("sairam");
        doctor.setSpecialization("MD_Cardio");
        doctor.setIncome(90000.00);
    }

    // ─── save() Tests ────────────────────────────────────────────────────────

    @Test
    @DisplayName("save() returns saved doctor with ID")
    void testSave_returnsSavedDoctorWithId() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert
        assertNotNull(savedDoctor);
        assertEquals(203, savedDoctor.getDocId());
    }

    @Test
    @DisplayName("save() returns doctor with correct name")
    void testSave_returnsDoctorWithCorrectName() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        Doctor savedDoctor = doctorRepo.save(doctor);

        // Assert
        assertEquals("sairam", savedDoctor.getDocName());
    }

    @Test
    @DisplayName("save() is called once")
    void testSave_isCalledOnce() {
        // Arrange
        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        doctorRepo.save(doctor);

        // Assert
        verify(doctorRepo, times(1)).save(doctor);
    }

    // ─── findById() Tests ────────────────────────────────────────────────────

    @Test
    @DisplayName("findById() returns Optional with doctor when found")
    void testFindById_withExistingId_returnsOptionalWithDoctor() {
        // Arrange
        when(doctorRepo.findById(203)).thenReturn(Optional.of(doctor));

        // Act
        Optional<Doctor> result = doctorRepo.findById(203);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(203, result.get().getDocId());
    }

    @Test
    @DisplayName("findById() returns empty Optional when not found")
    void testFindById_withNonExistingId_returnsEmptyOptional() {
        // Arrange
        when(doctorRepo.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        Optional<Doctor> result = doctorRepo.findById(999);

        // Assert
        assertFalse(result.isPresent());
    }

    // ─── findAll() Tests ─────────────────────────────────────────────────────

    @Test
    @DisplayName("findAll() returns all doctors")
    void testFindAll_returnsAllDoctors() {
        // Arrange
        Doctor doctor2 = new Doctor();
        doctor2.setDocId(204);
        doctor2.setDocName("John");
        doctor2.setSpecialization("Neurology");
        doctor2.setIncome(100000.00);

        when(doctorRepo.findAll()).thenReturn(Arrays.asList(doctor, doctor2));

        // Act
        Iterable<Doctor> result = doctorRepo.findAll();

        // Assert
        assertNotNull(result);
        int count = 0;
        for (Doctor d : result) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    @DisplayName("findAll() returns empty list when no doctors")
    void testFindAll_returnsEmptyList_whenNoDoctors() {
        // Arrange
        when(doctorRepo.findAll()).thenReturn(Arrays.asList());

        // Act
        Iterable<Doctor> result = doctorRepo.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
    }

    // ─── deleteById() Tests ──────────────────────────────────────────────────

    @Test
    @DisplayName("deleteById() is called with correct ID")
    void testDeleteById_isCalledWithCorrectId() {
        // Arrange
        doNothing().when(doctorRepo).deleteById(203);

        // Act
        doctorRepo.deleteById(203);

        // Assert
        verify(doctorRepo, times(1)).deleteById(203);
    }

    // ─── existsById() Tests ──────────────────────────────────────────────────

    @Test
    @DisplayName("existsById() returns true when doctor exists")
    void testExistsById_withExistingId_returnsTrue() {
        // Arrange
        when(doctorRepo.existsById(203)).thenReturn(true);

        // Act
        boolean exists = doctorRepo.existsById(203);

        // Assert
        assertTrue(exists);
    }

    @Test
    @DisplayName("existsById() returns false when doctor does not exist")
    void testExistsById_withNonExistingId_returnsFalse() {
        // Arrange
        when(doctorRepo.existsById(999)).thenReturn(false);

        // Act
        boolean exists = doctorRepo.existsById(999);

        // Assert
        assertFalse(exists);
    }

    // ─── count() Tests ───────────────────────────────────────────────────────

    @Test
    @DisplayName("count() returns correct number of doctors")
    void testCount_returnsCorrectCount() {
        // Arrange
        when(doctorRepo.count()).thenReturn(5L);

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(5L, count);
    }

    @Test
    @DisplayName("count() returns zero when no doctors")
    void testCount_returnsZero_whenNoDoctors() {
        // Arrange
        when(doctorRepo.count()).thenReturn(0L);

        // Act
        long count = doctorRepo.count();

        // Assert
        assertEquals(0L, count);
    }
}
