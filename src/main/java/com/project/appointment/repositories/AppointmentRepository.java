package com.project.appointment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment.models.Appointment;;

@Repository 												
public interface AppointmentRepository extends CrudRepository <Appointment,Long>{

    @Query(value="SELECT * FROM appointment", nativeQuery=true)
    List<Appointment> getAllAppointments();
    
}
