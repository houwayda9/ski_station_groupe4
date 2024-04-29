package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstructorServicesImplTest {
    @Autowired
    private InstructorServicesImpl instructorService;

//    @Autowired
//    private CourseServicesImpl courseService;

    @Autowired
    private ICourseRepository courseRepository;

    @Test
    void testAddInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        Instructor savedInstructor = instructorService.addInstructor(instructor);

        assertNotNull(savedInstructor);

        assertNotNull(savedInstructor.getNumInstructor());
    }

    @Test
    void testRetrieveAllInstructors() {
        List<Instructor> instructors = instructorService.retrieveAllInstructors();
        assertNotNull(instructors);
    }

    @Test
    void testUpdateInstructor() {

        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        Instructor savedInstructor = instructorService.addInstructor(instructor);

        savedInstructor.setFirstName("Jane");

        Instructor updatedInstructor = instructorService.updateInstructor(savedInstructor);

        assertNotNull(updatedInstructor);

        assertEquals("Jane", updatedInstructor.getFirstName());
    }

    @Test
    void testRetrieveInstructor() {

        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        Instructor savedInstructor = instructorService.addInstructor(instructor);

        Instructor retrievedInstructor = instructorService.retrieveInstructor(savedInstructor.getNumInstructor());
        assertNotNull(retrievedInstructor);
    }

    @Test
    void testAddInstructorAndAssignToCourse() {
        Course course = new Course();
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.INDIVIDUAL);
        course.setSupport(Support.SKI);
        course.setPrice(100.0f);
        course.setTimeSlot(3);
        course.setRegistrations(new HashSet<>());

        Course savedCourse = courseRepository.save(course);

        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>());

        Instructor addedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, savedCourse.getNumCourse());

        assertNotNull(addedInstructor);

        //assertTrue(addedInstructor.getCourses().contains(savedCourse));
    }
}
