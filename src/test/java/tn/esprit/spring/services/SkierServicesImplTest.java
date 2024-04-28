package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

@ContextConfiguration(classes = {SkierServicesImpl.class})
@ExtendWith(SpringExtension.class)
class SkierServicesImplTest {
    @MockBean
    private ICourseRepository iCourseRepository;

    @MockBean
    private IPisteRepository iPisteRepository;

    @MockBean
    private IRegistrationRepository iRegistrationRepository;

    @MockBean
    private ISkierRepository iSkierRepository;

    @MockBean
    private ISubscriptionRepository iSubscriptionRepository;

    @Autowired
    private SkierServicesImpl skierServicesImpl;

    /**
     * Method under test: {@link SkierServicesImpl#retrieveAllSkiers()}
     */
    @Test
    void testRetrieveAllSkiers() {
        // Arrange
        ArrayList<Skier> skierList = new ArrayList<>();
        when(iSkierRepository.findAll()).thenReturn(skierList);

        // Act
        List<Skier> actualRetrieveAllSkiersResult = skierServicesImpl.retrieveAllSkiers();

        // Assert
        verify(iSkierRepository).findAll();
        assertTrue(actualRetrieveAllSkiersResult.isEmpty());
        assertSame(skierList, actualRetrieveAllSkiersResult);
    }

    /**
     * Method under test: {@link SkierServicesImpl#retrieveAllSkiers()}
     */
    @Test
    void testRetrieveAllSkiers2() {
        // Arrange
        when(iSkierRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.retrieveAllSkiers());
        verify(iSkierRepository).findAll();
    }

    /**
     * Method under test: {@link SkierServicesImpl#addSkier(Skier)}
     */
    @Test
    void testAddSkier() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setNumSub(1L);
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setNumSkier(1L);
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        // Act
        Skier actualAddSkierResult = skierServicesImpl.addSkier(skier2);

        // Assert
        verify(iSkierRepository).save(isA(Skier.class));
        assertEquals("1971-01-01", skier2.getSubscription().getEndDate().toString());
        assertSame(skier, actualAddSkierResult);
    }

    /**
     * Method under test: {@link SkierServicesImpl#addSkier(Skier)}
     */
    @Test
    void testAddSkier2() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setNumSub(1L);
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.MONTHLY);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setNumSkier(1L);
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        // Act
        Skier actualAddSkierResult = skierServicesImpl.addSkier(skier2);

        // Assert
        verify(iSkierRepository).save(isA(Skier.class));
        assertEquals("1970-02-01", skier2.getSubscription().getEndDate().toString());
        assertSame(skier, actualAddSkierResult);
    }

    /**
     * Method under test: {@link SkierServicesImpl#addSkier(Skier)}
     */
    @Test
    void testAddSkier3() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setNumSub(1L);
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.SEMESTRIEL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setNumSkier(1L);
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        // Act
        Skier actualAddSkierResult = skierServicesImpl.addSkier(skier2);

        // Assert
        verify(iSkierRepository).save(isA(Skier.class));
        assertEquals("1970-07-01", skier2.getSubscription().getEndDate().toString());
        assertSame(skier, actualAddSkierResult);
    }

    /**
     * Method under test: {@link SkierServicesImpl#addSkier(Skier)}
     */
//    @Test
//    void testAddSkier4() {
//        // Arrange
//        when(iSkierRepository.save(Mockito.<Skier>any())).thenThrow(new IllegalArgumentException("foo"));
//
//        Subscription subscription = new Subscription();
//        subscription.setEndDate(LocalDate.of(1970, 1, 1));
//        subscription.setNumSub(1L);
//        subscription.setPrice(10.0f);
//        subscription.setStartDate(LocalDate.of(1970, 1, 1));
//        subscription.setTypeSub(TypeSubscription.ANNUAL);
//
//        Skier skier = new Skier();
//        skier.setCity("Oxford");
//        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
//        skier.setFirstName("Jane");
//        skier.setLastName("Doe");
//        skier.setNumSkier(1L);
//        skier.setPistes(new HashSet<>());
//        skier.setRegistrations(new HashSet<>());
//        skier.setSubscription(subscription);
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.addSkier(skier));
//        verify(iSkierRepository).save(isA(Skier.class));
//    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#assignSkierToSubscription(Long, Long)}
     */
    @Test
    void testAssignSkierToSubscription() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setNumSub(1L);
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setNumSkier(1L);
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);
        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier2);
        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Subscription subscription3 = new Subscription();
        subscription3.setEndDate(LocalDate.of(1970, 1, 1));
        subscription3.setNumSub(1L);
        subscription3.setPrice(10.0f);
        subscription3.setStartDate(LocalDate.of(1970, 1, 1));
        subscription3.setTypeSub(TypeSubscription.ANNUAL);
        Optional<Subscription> ofResult2 = Optional.of(subscription3);
        when(iSubscriptionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        Skier actualAssignSkierToSubscriptionResult = skierServicesImpl.assignSkierToSubscription(1L, 1L);

        // Assert
        verify(iSkierRepository).findById(eq(1L));
        verify(iSubscriptionRepository).findById(eq(1L));
        verify(iSkierRepository).save(isA(Skier.class));
        assertSame(skier2, actualAssignSkierToSubscriptionResult);
    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#assignSkierToSubscription(Long, Long)}
     */
//    @Test
//    void testAssignSkierToSubscription2() {
//        // Arrange
//        Subscription subscription = new Subscription();
//        subscription.setEndDate(LocalDate.of(1970, 1, 1));
//        subscription.setNumSub(1L);
//        subscription.setPrice(10.0f);
//        subscription.setStartDate(LocalDate.of(1970, 1, 1));
//        subscription.setTypeSub(TypeSubscription.ANNUAL);
//
//        Skier skier = new Skier();
//        skier.setCity("Oxford");
//        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
//        skier.setFirstName("Jane");
//        skier.setLastName("Doe");
//        skier.setNumSkier(1L);
//        skier.setPistes(new HashSet<>());
//        skier.setRegistrations(new HashSet<>());
//        skier.setSubscription(subscription);
//        Optional<Skier> ofResult = Optional.of(skier);
//        when(iSkierRepository.save(Mockito.<Skier>any())).thenThrow(new IllegalArgumentException("foo"));
//        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
//
//        Subscription subscription2 = new Subscription();
//        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
//        subscription2.setNumSub(1L);
//        subscription2.setPrice(10.0f);
//        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
//        subscription2.setTypeSub(TypeSubscription.ANNUAL);
//        Optional<Subscription> ofResult2 = Optional.of(subscription2);
//        when(iSubscriptionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.assignSkierToSubscription(1L, 1L));
//        verify(iSkierRepository).findById(eq(1L));
//        verify(iSubscriptionRepository).findById(eq(1L));
//        verify(iSkierRepository).save(isA(Skier.class));
//    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#assignSkierToSubscription(Long, Long)}
     */
    @Test
    void testAssignSkierToSubscription3() {
        // Arrange
        Optional<Skier> emptyResult = Optional.empty();
        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        Optional<Subscription> ofResult = Optional.of(subscription);
        when(iSubscriptionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.assignSkierToSubscription(1L, 1L));
        verify(iSkierRepository).findById(eq(1L));
        verify(iSubscriptionRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#assignSkierToSubscription(Long, Long)}
     */
    @Test
    void testAssignSkierToSubscription4() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);
        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Subscription> emptyResult = Optional.empty();
        when(iSubscriptionRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.assignSkierToSubscription(1L, 1L));
        verify(iSkierRepository).findById(eq(1L));
        verify(iSubscriptionRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#addSkierAndAssignToCourse(Skier, Long)}
     */
    @Test
    void testAddSkierAndAssignToCourse() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier);

        Course course = new Course();
        course.setLevel(1);
        course.setNumCourse(1L);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        when(iCourseRepository.getById(Mockito.<Long>any())).thenReturn(course);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setNumSub(1L);
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setNumSkier(1L);
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);

        // Act
        Skier actualAddSkierAndAssignToCourseResult = skierServicesImpl.addSkierAndAssignToCourse(skier2, 1L);

        // Assert
        verify(iCourseRepository).getById(eq(1L));
        verify(iSkierRepository).save(isA(Skier.class));
        assertSame(skier, actualAddSkierAndAssignToCourseResult);
    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#addSkierAndAssignToCourse(Skier, Long)}
     */
//    @Test
//    void testAddSkierAndAssignToCourse2() {
//        // Arrange
//        Subscription subscription = new Subscription();
//        subscription.setEndDate(LocalDate.of(1970, 1, 1));
//        subscription.setNumSub(1L);
//        subscription.setPrice(10.0f);
//        subscription.setStartDate(LocalDate.of(1970, 1, 1));
//        subscription.setTypeSub(TypeSubscription.ANNUAL);
//
//        Skier skier = new Skier();
//        skier.setCity("Oxford");
//        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
//        skier.setFirstName("Jane");
//        skier.setLastName("Doe");
//        skier.setNumSkier(1L);
//        skier.setPistes(new HashSet<>());
//        skier.setRegistrations(new HashSet<>());
//        skier.setSubscription(subscription);
//        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier);
//        when(iCourseRepository.getById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("foo"));
//
//        Subscription subscription2 = new Subscription();
//        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
//        subscription2.setNumSub(1L);
//        subscription2.setPrice(10.0f);
//        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
//        subscription2.setTypeSub(TypeSubscription.ANNUAL);
//
//        Skier skier2 = new Skier();
//        skier2.setCity("Oxford");
//        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
//        skier2.setFirstName("Jane");
//        skier2.setLastName("Doe");
//        skier2.setNumSkier(1L);
//        skier2.setPistes(new HashSet<>());
//        skier2.setRegistrations(new HashSet<>());
//        skier2.setSubscription(subscription2);
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.addSkierAndAssignToCourse(skier2, 1L));
//        verify(iCourseRepository).getById(eq(1L));
//        verify(iSkierRepository).save(isA(Skier.class));
//    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#addSkierAndAssignToCourse(Skier, Long)}
     */
    @Test
    void testAddSkierAndAssignToCourse3() {
        // Arrange
        Course course = new Course();
        course.setLevel(1);
        course.setNumCourse(1L);
        course.setPrice(10.0f);
        course.setRegistrations(new HashSet<>());
        course.setSupport(Support.SKI);
        course.setTimeSlot(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);

        Registration registration = new Registration();
        registration.setCourse(course);
        registration.setNumRegistration(1L);
        registration.setNumWeek(10);
        registration.setSkier(skier);

        HashSet<Registration> registrations = new HashSet<>();
        registrations.add(registration);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setNumSub(1L);
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setNumSkier(1L);
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(registrations);
        skier2.setSubscription(subscription2);
        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier2);

        Course course2 = new Course();
        course2.setLevel(1);
        course2.setNumCourse(1L);
        course2.setPrice(10.0f);
        course2.setRegistrations(new HashSet<>());
        course2.setSupport(Support.SKI);
        course2.setTimeSlot(1);
        course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        when(iCourseRepository.getById(Mockito.<Long>any())).thenReturn(course2);

        Course course3 = new Course();
        course3.setLevel(1);
        course3.setNumCourse(1L);
        course3.setPrice(10.0f);
        course3.setRegistrations(new HashSet<>());
        course3.setSupport(Support.SKI);
        course3.setTimeSlot(1);
        course3.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

        Subscription subscription3 = new Subscription();
        subscription3.setEndDate(LocalDate.of(1970, 1, 1));
        subscription3.setNumSub(1L);
        subscription3.setPrice(10.0f);
        subscription3.setStartDate(LocalDate.of(1970, 1, 1));
        subscription3.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier3 = new Skier();
        skier3.setCity("Oxford");
        skier3.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier3.setFirstName("Jane");
        skier3.setLastName("Doe");
        skier3.setNumSkier(1L);
        skier3.setPistes(new HashSet<>());
        skier3.setRegistrations(new HashSet<>());
        skier3.setSubscription(subscription3);

        Registration registration2 = new Registration();
        registration2.setCourse(course3);
        registration2.setNumRegistration(1L);
        registration2.setNumWeek(10);
        registration2.setSkier(skier3);
        when(iRegistrationRepository.save(Mockito.<Registration>any())).thenReturn(registration2);

        Subscription subscription4 = new Subscription();
        subscription4.setEndDate(LocalDate.of(1970, 1, 1));
        subscription4.setNumSub(1L);
        subscription4.setPrice(10.0f);
        subscription4.setStartDate(LocalDate.of(1970, 1, 1));
        subscription4.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier4 = new Skier();
        skier4.setCity("Oxford");
        skier4.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier4.setFirstName("Jane");
        skier4.setLastName("Doe");
        skier4.setNumSkier(1L);
        skier4.setPistes(new HashSet<>());
        skier4.setRegistrations(new HashSet<>());
        skier4.setSubscription(subscription4);

        // Act
        Skier actualAddSkierAndAssignToCourseResult = skierServicesImpl.addSkierAndAssignToCourse(skier4, 1L);

        // Assert
        verify(iCourseRepository).getById(eq(1L));
        verify(iRegistrationRepository).save(isA(Registration.class));
        verify(iSkierRepository).save(isA(Skier.class));
        assertSame(skier2, actualAddSkierAndAssignToCourseResult);
    }

    /**
     * Method under test: {@link SkierServicesImpl#removeSkier(Long)}
     */
    @Test
    void testRemoveSkier() {
        // Arrange
        doNothing().when(iSkierRepository).deleteById(Mockito.<Long>any());

        // Act
        skierServicesImpl.removeSkier(1L);

        // Assert that nothing has changed
        verify(iSkierRepository).deleteById(eq(1L));
    }

    /**
     * Method under test: {@link SkierServicesImpl#removeSkier(Long)}
     */
//    @Test
//    void testRemoveSkier2() {
//        // Arrange
//        doThrow(new IllegalArgumentException("foo")).when(iSkierRepository).deleteById(Mockito.<Long>any());
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.removeSkier(1L));
//        verify(iSkierRepository).deleteById(eq(1L));
//    }

    /**
     * Method under test: {@link SkierServicesImpl#retrieveSkier(Long)}
     */
    @Test
    void testRetrieveSkier() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);
        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Skier actualRetrieveSkierResult = skierServicesImpl.retrieveSkier(1L);

        // Assert
        verify(iSkierRepository).findById(eq(1L));
        assertSame(skier, actualRetrieveSkierResult);
    }

    /**
     * Method under test: {@link SkierServicesImpl#retrieveSkier(Long)}
     */
//    @Test
//    void testRetrieveSkier2() {
//        // Arrange
//        when(iSkierRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("foo"));
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.retrieveSkier(1L));
//        verify(iSkierRepository).findById(eq(1L));
//    }

    /**
     * Method under test: {@link SkierServicesImpl#assignSkierToPiste(Long, Long)}
     */
    @Test
    void testAssignSkierToPiste() {
        // Arrange
        Subscription subscription = new Subscription();
        subscription.setEndDate(LocalDate.of(1970, 1, 1));
        subscription.setNumSub(1L);
        subscription.setPrice(10.0f);
        subscription.setStartDate(LocalDate.of(1970, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier = new Skier();
        skier.setCity("Oxford");
        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier.setFirstName("Jane");
        skier.setLastName("Doe");
        skier.setNumSkier(1L);
        skier.setPistes(new HashSet<>());
        skier.setRegistrations(new HashSet<>());
        skier.setSubscription(subscription);
        Optional<Skier> ofResult = Optional.of(skier);

        Subscription subscription2 = new Subscription();
        subscription2.setEndDate(LocalDate.of(1970, 1, 1));
        subscription2.setNumSub(1L);
        subscription2.setPrice(10.0f);
        subscription2.setStartDate(LocalDate.of(1970, 1, 1));
        subscription2.setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.setCity("Oxford");
        skier2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        skier2.setFirstName("Jane");
        skier2.setLastName("Doe");
        skier2.setNumSkier(1L);
        skier2.setPistes(new HashSet<>());
        skier2.setRegistrations(new HashSet<>());
        skier2.setSubscription(subscription2);
        when(iSkierRepository.save(Mockito.<Skier>any())).thenReturn(skier2);
        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Piste piste = new Piste();
        piste.setColor(Color.GREEN);
        piste.setLength(3);
        piste.setNamePiste("Name Piste");
        piste.setNumPiste(1L);
        piste.setSkiers(new HashSet<>());
        piste.setSlope(1);
        Optional<Piste> ofResult2 = Optional.of(piste);
        when(iPisteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        // Act
        Skier actualAssignSkierToPisteResult = skierServicesImpl.assignSkierToPiste(1L, 1L);

        // Assert
        verify(iPisteRepository).findById(eq(1L));
        verify(iSkierRepository).findById(eq(1L));
        verify(iSkierRepository).save(isA(Skier.class));
        assertSame(skier2, actualAssignSkierToPisteResult);
    }

    /**
     * Method under test: {@link SkierServicesImpl#assignSkierToPiste(Long, Long)}
     */
//    @Test
//    void testAssignSkierToPiste2() {
//        // Arrange
//        Subscription subscription = new Subscription();
//        subscription.setEndDate(LocalDate.of(1970, 1, 1));
//        subscription.setNumSub(1L);
//        subscription.setPrice(10.0f);
//        subscription.setStartDate(LocalDate.of(1970, 1, 1));
//        subscription.setTypeSub(TypeSubscription.ANNUAL);
//
//        Skier skier = new Skier();
//        skier.setCity("Oxford");
//        skier.setDateOfBirth(LocalDate.of(1970, 1, 1));
//        skier.setFirstName("Jane");
//        skier.setLastName("Doe");
//        skier.setNumSkier(1L);
//        skier.setPistes(new HashSet<>());
//        skier.setRegistrations(new HashSet<>());
//        skier.setSubscription(subscription);
//        Optional<Skier> ofResult = Optional.of(skier);
//        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
//        when(iPisteRepository.findById(Mockito.<Long>any())).thenThrow(new IllegalArgumentException("foo"));
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.assignSkierToPiste(1L, 1L));
//        verify(iPisteRepository).findById(eq(1L));
//        verify(iSkierRepository).findById(eq(1L));
//    }

    /**
     * Method under test: {@link SkierServicesImpl#assignSkierToPiste(Long, Long)}
     */
    @Test
    void testAssignSkierToPiste3() {
        // Arrange
        Optional<Skier> emptyResult = Optional.empty();
        when(iSkierRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Piste piste = new Piste();
        piste.setColor(Color.GREEN);
        piste.setLength(3);
        piste.setNamePiste("Name Piste");
        piste.setNumPiste(1L);
        piste.setSkiers(new HashSet<>());
        piste.setSlope(1);
        Optional<Piste> ofResult = Optional.of(piste);
        when(iPisteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> skierServicesImpl.assignSkierToPiste(1L, 1L));
        verify(iPisteRepository).findById(eq(1L));
        verify(iSkierRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#retrieveSkiersBySubscriptionType(TypeSubscription)}
     */
    @Test
    void testRetrieveSkiersBySubscriptionType() {
        // Arrange
        ArrayList<Skier> skierList = new ArrayList<>();
        when(iSkierRepository.findBySubscription_TypeSub(Mockito.<TypeSubscription>any())).thenReturn(skierList);

        // Act
        List<Skier> actualRetrieveSkiersBySubscriptionTypeResult = skierServicesImpl
                .retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        // Assert
        verify(iSkierRepository).findBySubscription_TypeSub(eq(TypeSubscription.ANNUAL));
        assertTrue(actualRetrieveSkiersBySubscriptionTypeResult.isEmpty());
        assertSame(skierList, actualRetrieveSkiersBySubscriptionTypeResult);
    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#retrieveSkiersBySubscriptionType(TypeSubscription)}
     */
//    @Test
//    void testRetrieveSkiersBySubscriptionType2() {
//        // Arrange
//        ArrayList<Skier> skierList = new ArrayList<>();
//        when(iSkierRepository.findBySubscription_TypeSub(Mockito.<TypeSubscription>any())).thenReturn(skierList);
//
//        // Act
//        List<Skier> actualRetrieveSkiersBySubscriptionTypeResult = skierServicesImpl
//                .retrieveSkiersBySubscriptionType(TypeSubscription.MONTHLY);
//
//        // Assert
//        verify(iSkierRepository).findBySubscription_TypeSub(eq(TypeSubscription.MONTHLY));
//        assertTrue(actualRetrieveSkiersBySubscriptionTypeResult.isEmpty());
//        assertSame(skierList, actualRetrieveSkiersBySubscriptionTypeResult);
//    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#retrieveSkiersBySubscriptionType(TypeSubscription)}
     */
//    @Test
//    void testRetrieveSkiersBySubscriptionType3() {
//        // Arrange
//        ArrayList<Skier> skierList = new ArrayList<>();
//        when(iSkierRepository.findBySubscription_TypeSub(Mockito.<TypeSubscription>any())).thenReturn(skierList);
//
//        // Act
//        List<Skier> actualRetrieveSkiersBySubscriptionTypeResult = skierServicesImpl
//                .retrieveSkiersBySubscriptionType(TypeSubscription.SEMESTRIEL);
//
//        // Assert
//        verify(iSkierRepository).findBySubscription_TypeSub(eq(TypeSubscription.SEMESTRIEL));
//        assertTrue(actualRetrieveSkiersBySubscriptionTypeResult.isEmpty());
//        assertSame(skierList, actualRetrieveSkiersBySubscriptionTypeResult);
//    }

    /**
     * Method under test:
     * {@link SkierServicesImpl#retrieveSkiersBySubscriptionType(TypeSubscription)}
     */
//    @Test
//    void testRetrieveSkiersBySubscriptionType4() {
//        // Arrange
//        when(iSkierRepository.findBySubscription_TypeSub(Mockito.<TypeSubscription>any()))
//                .thenThrow(new IllegalArgumentException("foo"));
//
//        // Act and Assert
//        assertThrows(IllegalArgumentException.class,
//                () -> skierServicesImpl.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL));
//        verify(iSkierRepository).findBySubscription_TypeSub(eq(TypeSubscription.ANNUAL));
//    }
}
