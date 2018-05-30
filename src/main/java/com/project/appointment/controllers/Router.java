package com.project.appointment.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.project.appointment.models.Appointment;
import com.project.appointment.repositories.AppointmentRepository;
import com.project.appointment.services.AppointmentService;
import com.project.appointment.validators.AppointmentValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.appointment.validators.AppointmentValidator;

@Controller
@RequestMapping("/*") // Wildcard all routes.
public class Router{

	private AppointmentService _as;
	private AppointmentRepository _ar;
	private AppointmentValidator _av;
	// public ArrayList<String> newErrors; 
	public boolean newErrors = false;
	public boolean usingSearch = false;		
	public List<Appointment> newList;

	public Router(AppointmentService _as, AppointmentRepository _ar, AppointmentValidator _av){

		this._ar = _ar;
		this._av = _av;
		this._as = _as;
	
	}

	@RequestMapping("index")
	public String index(@ModelAttribute("appointment") Appointment appointment, Model model, Boolean newAppointment){

		// newAppointment = false;

	
		if (this.usingSearch == false) {

			List<Appointment> allAppointments = (List<Appointment>) _ar.getAllAppointments();

			List<Appointment> appointmentLoop = new ArrayList<Appointment>();
		
			for (Appointment iAppointment : allAppointments) {
				appointmentLoop.add(iAppointment);
				System.out.println(iAppointment);
			}

			model.addAttribute("appointmentLoop", appointmentLoop);
			model.addAttribute("someErrors", newErrors);
		} else{
			model.addAttribute("appointmentLoop", this.newList);
		}
		this.usingSearch = false;



		return "index";
	}

	@RequestMapping("")
	public String redirect(HttpServletRequest req){	
		newErrors = false;	
		String url = req.getRequestURI().toString();
		return "redirect:/index";
	}	

	@PostMapping("/newAppointment")
	public String createAppointment(@ModelAttribute("appointment") Appointment appointment, String sDate, String time, String description, BindingResult res, Model model) {


		_av.validate(appointment, res);

		if (res.hasErrors()) {

			System.out.println(res);
			// newErrors.add("Date cannot be in the past.");
			newErrors = true;
			System.out.println("Date cannot be in the past.");

			List<Appointment> appointments =(List<Appointment>) _ar.findAll();
			int numCopies = 0;

			// for (Appointment iAppointment : appointments) {
			// 	if(iAppointment.getTime() == time && iAppointment.getsDate() == sDate) {
			// 		numCopies++;
			// 	}
			// }

			// if(numCopies > 0) {
			// 	newErrors.add("Appointment already set at this date and time");
			// 	System.out.println("Appointment already set at this date and time");
			// }

			// if(appointment.getDescription().length() > 7 && appointment.getDescription().length() < 25) {
			// 	newErrors.add("Reason must be between 7-25 characters");
			// 	System.out.println("Reason must be between 7-25 characters.");
			// }

			return "redirect:/index";
		}

		else {

			List<Appointment> appointments =(List<Appointment>) _ar.findAll();
			int numCopies = 0;

			for (Appointment iAppointment : appointments) {
				if(iAppointment.getTime() == time && iAppointment.getsDate() == sDate) {
					numCopies++;
				}
			}

			_as.create(appointment, sDate, time, description);

		}

		return "redirect:/index";
	}

	@PostMapping("/search")
	public String searchByAppointment(Model model, @RequestParam("search") String term) {

		usingSearch = true;
		this.newList =  _as.searchByDescription(term);
		System.out.println("HELLOTHERE");
		System.out.println( _as.searchByDescription(term));

		return "redirect:/index";
	}
	
}
