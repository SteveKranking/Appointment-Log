<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="/js/main.js"></script>
	</head>

	<body>


		<script src= 'http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>

		<script> 
			
			$(document).ready(function() {

			$('#toggleTo').hide();
				
			$("#toggle").click(function() { 
				$("#toggleTo").toggle()
			});
			
			$("#toggleText").click(function() { 
				if ($(this).text() == "Schedule an Appointment") { 
					$(this).text("Cancel"); 
				} else { 
					$(this).text("Schedule an Appointment"); 
				}; 
			});

			});

		</script>

		<nav class="navbar navbar-expand-lg navbar-custom bg-custom">
			<img class="navbar-brand" id="logo" src="https://upload.wikimedia.org/wikipedia/commons/5/5c/AT%26T-logo_2016.png" />
		</nav>
		
		<div class="container spacing">
			<div class="row">
				<div class="col-md-12">
					<h1> Appointments </h1>
				</div>
			</div>
		</div>

		<div class="container spacing">
			<div class="row">
				<div class="col-md-12">
					<button class="btn btn-primary buttonText" path="/toggleAppointment" id="toggle" class"toggleAgain" type="submit"> <span id="toggleText">Schedule an Appointment</span> </button>
				</div>
			</div>
		</div>

			<div class="container spacing" id="toggleTo">
				<div class="row">
					<div class="col-md-12">

						<form:form method="POST" action="/newAppointment" modelAttribute="appointment">

							<form:label path="date"> Date </form:label>
							<form:input type="date" path="date" />
				
							<form:label path="time"> Time </form:label>
							<form:select path="time">
									<option value="9:00">9:00</option>
									<option value="9:30">9:30</option>
									<option value="10:00">10:00</option>
									<option value="10:30">10:30</option>
									<option value="11:00">11:00</option>
									<option value="11:30">11:30</option>
									<option value="12:00">12:00</option>
									<option value="12:30">12:30</option>
									<option value="1:00">1:00</option>
									<option value="1:30">1:30</option>
									<option value="2:00">2:00</option>
									<option value="2:30">2:30</option>
									<option value="3:00">3:00</option>
									<option value="3:30">3:30</option>
									<option value="4:00">4:00</option>
									<option value="4:30">4:30</option>
									<option value="5:00">5:00</option>
							</form:select>
				
							<form:label path="description"> Reason </form:label>
							<form:input type="text" path="description" />
						
							<input class="btn btn-primary" type="submit" value="Add" />
				
						</form:form>

					</div>
				</div>
			</div>

		<div class="container spacing">
			<div class="row">
				<div class="col-md-12">
					<form method="POST" action="/search">
						<label>Search for Appointments: <input name="search"/></label> <input class="btn btn-secondary" type="submit" value="search" />
					</form>
				</div>
			</div>
		</div>

		<div class="container spacing">
			<div class="row">
				<div class="col-md-12">

						<table>
							<thead>
								<tr>
									<td> Date </td>
									<td> Time </td>
									<td> Reason </td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${appointmentLoop}" var="thisAppointment">
									<tr>
										<td> ${thisAppointment.date} </td>
										<td> ${thisAppointment.time} </td>
										<td> ${thisAppointment.description} </td>
									</tr>
								</c:forEach>
							
							 <!-- <c:forEach items="${results}" var="appointment">
								<tr>
									<td>${ appointment.date }</td>
									<td>${ appointment.time }</td>
									<td>${ appointment.descripiton }</td>
								</tr>
							</c:forEach>  -->

							</tbody>
						</table>

				</div>
			</div>
		</div>

	</body>
</html>