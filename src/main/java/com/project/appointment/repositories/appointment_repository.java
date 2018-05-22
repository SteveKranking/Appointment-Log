package com.project.appointment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment.models.Appointment;

@Repository 												
public interface appointment_repository extends CrudRepository <Appointment ,Long>{
	// Query methods go here.
	
	// Example:
	// public YourModelHere findByName(String name);
}
