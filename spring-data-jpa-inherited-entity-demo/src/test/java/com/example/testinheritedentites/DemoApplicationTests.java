package com.example.testinheritedentites;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.example.testinheritedentites.entity.Doctor;
import com.example.testinheritedentites.entity.Patient;
import com.example.testinheritedentites.entity.User;
import com.example.testinheritedentites.repository.DoctorRepository;
import com.example.testinheritedentites.repository.PatientRepository;
import com.example.testinheritedentites.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import net.bytebuddy.utility.RandomString;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DemoApplicationTests {
  @Autowired
  TestEntityManager em;
  @Autowired
  UserRepository userRepository;
  @Autowired
  DoctorRepository doctorRepository;
  @Autowired
  PatientRepository patientRepository;

  private List<String> usernames =
      IntStream.rangeClosed(0, 1).mapToObj(i -> RandomString.make()).collect(Collectors.toList());


  @Before
  public void setUp() {
    presetUsers();
  }

  @Test
  public void setUserToDoctor_shouldSucceed() {
    final User user = userRepository.findByUsername(usernames.get(0)).get();
    changeUserType(user, User.Type.doctor);
    final Doctor doctor = doctorRepository.findByUsername(usernames.get(0)).get();
    assertThat(doctor.getUsername()).isEqualTo(user.getUsername());
    assertThat(doctor.getType()).isEqualTo(User.Type.doctor);
  }

  @Test
  public void setUserToPatient_shouldSucceed() {
    final User user = userRepository.findByUsername(usernames.get(0)).get();
    changeUserType(user, User.Type.patient);
    final Patient patient = patientRepository.findByUsername(usernames.get(0)).get();
    assertThat(patient.getUsername()).isEqualTo(user.getUsername());
    assertThat(patient.getType()).isEqualTo(User.Type.patient);
  }

  @Test
  public void setUserToDoctorAgain_shouldSucceed() {
    final User user = userRepository.findByUsername(usernames.get(0)).get();
    changeUserType(user, User.Type.doctor);
    changeUserType(user, User.Type.doctor);
    final Doctor doctor = doctorRepository.findByUsername(usernames.get(0)).get();
    assertThat(doctor.getUsername()).isEqualTo(user.getUsername());
    assertThat(doctor.getType()).isEqualTo(User.Type.doctor);
  }

  @Test
  public void setUserToPatientAgain_shouldSucceed() {
    final User user = userRepository.findByUsername(usernames.get(0)).get();
    changeUserType(user, User.Type.patient);
    changeUserType(user, User.Type.patient);
    final Patient patient = patientRepository.findByUsername(usernames.get(0)).get();
    assertThat(patient.getUsername()).isEqualTo(user.getUsername());
    assertThat(patient.getType()).isEqualTo(User.Type.patient);
  }

  @Test
  public void changeDoctorToPatient_shouldFailed() {
    final String username = usernames.get(0);
    final User user = userRepository.findByUsername(username).get();
    changeUserType(user, User.Type.doctor);
    final Throwable thrown = catchThrowable(() -> changeUserType(user, User.Type.patient));
    assertThat(thrown).isNotNull();
    em.clear();
    final Doctor doctor = doctorRepository.findByUsername(username).get();
    assertThat(doctor.getUsername()).isEqualTo(username);
    assertThat(doctor.getType()).isEqualTo(User.Type.doctor);
    em.clear();
    assertThat(patientRepository.findByUsername(username)).isEmpty();
  }

  @Test
  public void changePatientToDoctor_shouldFailed() {
    final String username = usernames.get(0);
    final User user = userRepository.findByUsername(username).get();
    changeUserType(user, User.Type.patient);
    final Throwable thrown = catchThrowable(() -> changeUserType(user, User.Type.doctor));
    assertThat(thrown).isNotNull();
    em.clear();
    final Patient patient = patientRepository.findByUsername(username).get();
    assertThat(patient.getUsername()).isEqualTo(username);
    assertThat(patient.getType()).isEqualTo(User.Type.patient);
    em.clear();
    assertThat(doctorRepository.findByUsername(username)).isEmpty();
  }

  private void presetUsers() {
    userRepository.saveAll(
        usernames.stream().map(username -> User.builder().username(username).build())::iterator);
  }

  private void changeUserType(final User user, final User.Type type) {
    userRepository.setUserType(user.getId(), type.name());
    em.clear();
  }
}
