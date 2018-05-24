package com.project.appointment.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Replace occurences of "YourModel" with a valid model
import com.project.appointment.models.Appointment;

@Component
public class AppointmentValidator implements Validator{	

	public AppointmentValidator() {
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Appointment.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Appointment appointment = (Appointment) object;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			appointment.setDate(format.parse(appointment.getsDate()));
		} catch(ParseException e) {
			e.printStackTrace();
		}
		Date today = new Date();
		System.out.println(today);
		System.out.println(appointment.getDate());
		if(appointment.getDate().before(today)) {
			System.out.println("Made it inside the if statement.");
			errors.rejectValue("date", "Before");
		}
	}
}
