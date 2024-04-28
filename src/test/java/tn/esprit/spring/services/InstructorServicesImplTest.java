package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class InstructorServicesImplTest {
    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private InstructorServicesImpl instructorServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddInstructor() {
        Instructor instructor = new Instructor(); // create an instructor object
        when(instructorRepository.save(instructor)).thenReturn(instructor); // mock the behavior of instructorRepository.save()

        Instructor savedInstructor = instructorServices.addInstructor(instructor);

        // Verify that the save method of instructorRepository is called once
        verify(instructorRepository, times(1)).save(instructor);

        // Assert that the returned instructor is the same as the one saved
        assertSame(instructor, savedInstructor);
    }

    @Test
    public void testRetrieveAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        when(instructorRepository.findAll()).thenReturn(instructors);

        List<Instructor> retrievedInstructors = instructorServices.retrieveAllInstructors();

        // Verify that the findAll method of instructorRepository is called once
        verify(instructorRepository, times(1)).findAll();

        // Assert that the returned list of instructors is the same as the mocked one
        assertSame(instructors, retrievedInstructors);
    }

    @Test
    public void testUpdateInstructor() {
        Instructor instructor = new Instructor(); // create an instructor object
        when(instructorRepository.save(instructor)).thenReturn(instructor); // mock the behavior of instructorRepository.save()

        Instructor updatedInstructor = instructorServices.updateInstructor(instructor);

        // Verify that the save method of instructorRepository is called once
        verify(instructorRepository, times(1)).save(instructor);

        // Assert that the returned instructor is the same as the one updated
        assertSame(instructor, updatedInstructor);
    }

    @Test
    public void testRetrieveInstructor() {
        Long numInstructor = 1L;
        Instructor instructor = new Instructor();
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));

        Instructor retrievedInstructor = instructorServices.retrieveInstructor(numInstructor);

        // Verify that the findById method of instructorRepository is called once
        verify(instructorRepository, times(1)).findById(numInstructor);

        // Assert that the returned instructor is the same as the one retrieved
        assertSame(instructor, retrievedInstructor);
    }



}