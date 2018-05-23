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

@Controller
@RequestMapping("/*") // Wildcard all routes.
public class Router{

	private AppointmentService _as;
	private AppointmentRepository _ar;

	public Router(AppointmentService _as, AppointmentRepository _ar){

		this._ar = _ar;
		this._as = _as;
	
	}

	@RequestMapping("index")
	public String index(@ModelAttribute("appointment") Appointment appointment, Model model, Boolean newAppointment){

		newAppointment = false;

		List<Appointment> allAppointments = (List<Appointment>) _ar.getAllAppointments();

		List<Appointment> appointmentLoop = new ArrayList<Appointment>();
		
		for (Appointment iAppointment : allAppointments) {
			appointmentLoop.add(iAppointment);
			System.out.println(iAppointment);
		}

		model.addAttribute("appointmentLoop", appointmentLoop);

		return "index";
	}

	@RequestMapping("")
	public String redirect(HttpServletRequest req){		
		String url = req.getRequestURI().toString();
		return "redirect:/index";
	}	

	@PostMapping("/newAppointment")
	public String createAppointment(@ModelAttribute("appointment") Appointment appointment, String date, String time, String description, BindingResult res) {
		
		_as.create(appointment, date, time, description);

		return "redirect:/index";
	}

	@PostMapping("/search")
	public String searchByArtist(Model model, @RequestParam("search") String term) {
		model.addAttribute("term", term);
		model.addAttribute("results", _as.searchByDescription(term));
		return "index";
	}

	@RequestMapping("/toggleAppointment")
	public String toggleAppointment(Model model, Boolean newAppointment) {
		model.addAttribute("newAppointment", newAppointment);

		newAppointment = !newAppointment;

		return "index";
	}

	
}
