package com.project.appointment.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// Replace occurences of "YourModel" with a valid model
import com.project.appointment.models.Appointment;

@Component
public class appointment_validator implements Validator{	
	@Override
	public boolean supports(Class<?> clazz) {
		return Appointment.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Appointment appointment = (Appointment) object;
		
		// Example:
		// if(!Appointment.getSomeField().equals(Appointment.getSomeOtherField())){
		// 	errors.rejectValue("someField","Match");
		// }
	}
}
