package com.project.appointment.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.appointment.models.Appointment;
import com.project.appointment.repositories.AppointmentRepository;;

@Service
public class AppointmentService {
	// Member variables / service initializations go here

	private AppointmentRepository _ar;
		
	public AppointmentService(AppointmentRepository _ar){

		this._ar = _ar;

	}

	public void create(Appointment appointment, String sDate, String time, String description){
		
		appointment.setsDate(sDate);
		appointment.setTime(time);
		appointment.setDescription(description);
		_ar.save(appointment);
		
	}

	public List<Appointment> searchByDescription(String search) {
		return _ar.findByDescriptionContaining(search);
	}
	
	// Crud methods to act on services go here.
}
