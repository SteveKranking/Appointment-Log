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

	public void create(Appointment appointment, String date, String time, String desc){
		
		appointment.setDate(date);
		appointment.setTime(time);
		appointment.setDesc(desc);
		_ar.save(appointment);
		
	}
	
	// Crud methods to act on services go here.
}
