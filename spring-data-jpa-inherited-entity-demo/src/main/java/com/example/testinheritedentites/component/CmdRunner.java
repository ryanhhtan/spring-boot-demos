package com.example.testinheritedentites.component;

import java.util.Arrays;
import java.util.List;
import com.example.testinheritedentites.entity.Doctor;
import com.example.testinheritedentites.entity.Patient;
import com.example.testinheritedentites.entity.User;
import com.example.testinheritedentites.repository.DoctorRepository;
import com.example.testinheritedentites.repository.PatientRepository;
import com.example.testinheritedentites.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CmdRunner
 */
@Component
@RequiredArgsConstructor
@Profile("cmd")
@Slf4j
public class CmdRunner implements CommandLineRunner {
  private List<String> usernames = Arrays.asList("user1", "user2");

  @NonNull
  UserRepository userRepository;
  @NonNull
  DoctorRepository doctorRepository;
  @NonNull
  PatientRepository patientRepository;

  @Override
  public void run(String... args) throws Exception {
    presetUsers();
    changeUser1ToDoctor();
    changeUser1ToDoctorAgain();
    changeUser2ToPatient();
    changeUser2ToPatientAgain();
    try {
      // change a doctor to patient is not allowed
      changeDoctorToPatient();
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      // change a patient to doctor is not allowed
      changePatientToDoctor();

    } catch (Exception e) {
      e.printStackTrace();
    }
    usernames.stream().map(username -> userRepository.findByUsername(username).get())
        .forEach(user -> log.info("user: {}", user));;
  }

  private void changeDoctorToPatient() {
    final Doctor doctor = doctorRepository.findAll().get(0);
    userRepository.setUserType(doctor.getId(), User.Type.patient.name());
  }

  private void changePatientToDoctor() {
    final Patient patient = patientRepository.findAll().get(0);
    userRepository.setUserType(patient.getId(), User.Type.doctor.name());
  }

  private void changeUser2ToPatientAgain() {
    changeUser2ToPatient();
  }

  private void presetUsers() {
    userRepository.saveAll(
        usernames.stream().map(username -> User.builder().username(username).build())::iterator);
    userRepository.flush();
  }

  private void changeUser1ToDoctor() {
    final String username = usernames.get(0);
    final User user = userRepository.findByUsername(username).get();
    userRepository.setUserType(user.getId(), User.Type.doctor.name());
    final Doctor doctor = doctorRepository.findByUsername(username).get();
    doctor.setHospital("hospital1").setType(User.Type.doctor);
    userRepository.save(doctor);
  }

  private void changeUser1ToDoctorAgain() {
    changeUser1ToDoctor();
  }

  private void changeUser2ToPatient() {
    final String username = usernames.get(1);
    final User user = userRepository.findByUsername(username).get();
    userRepository.setUserType(user.getId(), User.Type.patient.name());
    final Patient patient = patientRepository.findByUsername(username).get();
    patient.setAddress("1234 main street");
    userRepository.save(patient);
  }
}
