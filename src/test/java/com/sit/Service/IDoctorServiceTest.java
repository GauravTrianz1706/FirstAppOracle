package com.sit.Service;

import com.sit.entity.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for IDoctorService interface contract.
 * Verifies the interface contract using an anonymous implementation.
 */
@DisplayName("IDoctorService Interface Tests")
class IDoctorServiceTest {

    // ─── Interface Contract Tests ────────────────────────────────────────────

    @Test
    @DisplayName("IDoctorService implementation can register a doctor")
    void testRegisterDoctor_interfaceContract_returnsString() {
        // Arrange - anonymous implementation of the interface
        IDoctorService service = doctor -> "Doctor obj is saved with id value :" + doctor.getDocId();

        Doctor doc = new Doctor();
        doc.setDocId(100);
        doc.setDocName("TestDoctor");
        doc.setSpecialization("Cardiology");
        doc.setIncome(80000.00);

        // Act
        String result = service.registerDoctor(doc);

        // Assert
        assertNotNull(result);
        assertEquals("Doctor obj is saved with id value :100", result);
    }

    @Test
    @DisplayName("IDoctorService registerDoctor returns non-null result")
    void testRegisterDoctor_returnsNonNull() {
        // Arrange
        IDoctorService service = doctor -> "Saved:" + doctor.getDocId();

        Doctor doc = new Doctor();
        doc.setDocId(1);

        // Act
        String result = service.registerDoctor(doc);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("IDoctorService is a functional interface with single abstract method")
    void testIDoctorService_isFunctionalInterface() {
        // Arrange - lambda can be assigned to IDoctorService (functional interface)
        IDoctorService service = doctor -> "OK";

        Doctor doc = new Doctor();
        doc.setDocId(1);

        // Act
        String result = service.registerDoctor(doc);

        // Assert
        assertEquals("OK", result);
    }

    @Test
    @DisplayName("IDoctorService registerDoctor with null doctor - implementation handles it")
    void testRegisterDoctor_withCustomImplementation_handlesNullDoctor() {
        // Arrange
        IDoctorService service = doctor -> {
            if (doctor == null) {
                return "Doctor is null";
            }
            return "Doctor obj is saved with id value :" + doctor.getDocId();
        };

        // Act
        String result = service.registerDoctor(null);

        // Assert
        assertEquals("Doctor is null", result);
    }
}
