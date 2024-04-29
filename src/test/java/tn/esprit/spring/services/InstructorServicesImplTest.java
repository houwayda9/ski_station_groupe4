package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class InstructorImplTest {

    @InjectMocks
    private InstructorServicesImpl instructorService;

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Test
    void testAddInstructor() {

        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor savedInstructor = instructorService.addInstructor(instructor);

        verify(instructorRepository, times(1)).save(any(Instructor.class));

        assertNotNull(savedInstructor);
    }

    @Test
    void testRetrieveAllInstructors() {
        List <Instructor> instructors = new ArrayList<>();
        when(instructorRepository.findAll()).thenReturn(instructors);

        List <Instructor> returnedInstructors = instructorService.retrieveAllInstructors();

        verify(instructorRepository, times(1)).findAll();
        assertNotNull(returnedInstructors);
    }

    @Test
    void testUpdateInstructor() {
        Instructor instructor = new Instructor();
        instructor.setNumInstructor(1L);
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor updatedInstructor = instructorService.updateInstructor(instructor);

        verify(instructorRepository, times(1)).save(instructor);
        assertNotNull(updatedInstructor);
    }

    @Test
    void testRetrieveInstructor() {

        Instructor instructor = new Instructor();
        instructor.setNumInstructor(1L); // Assuming this instructor already exists
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        Instructor retrievedInstructor = instructorService.retrieveInstructor(1L);

        verify(instructorRepository, times(1)).findById(1L);

        assertNotNull(retrievedInstructor);
    }

    @Test
    void testAddInstructorAndAssignToCourse() {
        Course course = new Course();
        course.setNumCourse(1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor addedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, 1L);

        verify(courseRepository, times(1)).findById(1L);

        verify(instructorRepository, times(1)).save(instructor);

        assertNotNull(addedInstructor);
        assertTrue(addedInstructor.getCourses().contains(course));
    }

}
