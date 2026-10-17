
package com.smartclinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT DISTINCT d FROM Doctor d JOIN d.availableTimes t " +
           "WHERE LOWER(d.speciality) = LOWER(:speciality) AND LOWER(t) LIKE LOWER(CONCAT('%', :time, '%'))")
    List<Doctor> findBySpecialityAndAvailableTime(
            @Param("speciality") String speciality,
            @Param("time") String time);

    List<Doctor> findBySpecialityIgnoreCase(String speciality);
}
