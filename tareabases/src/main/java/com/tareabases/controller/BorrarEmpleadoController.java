package com.tareabases.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tareabases.bussines.EmpleadoService;
import com.tareabases.domain.Empleado;

@Controller
public class BorrarEmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	String cedula;
	private Empleado empleadoBorrar;
	
	@RequestMapping(value="/eliminarEmpleado/{id}/**", method = RequestMethod.GET)
	public String remove(Model model, @PathVariable String id, HttpServletRequest request){
		String cedulaBorrar = new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI());
		
		empleadoBorrar = empleadoService.findEmpleadoByCedula(cedulaBorrar);
		cedula= empleadoBorrar.getCedula();
		model.addAttribute("empleadoBorrar", empleadoBorrar);
		return "eliminarEmpleado";
	}
	
	@RequestMapping(value="/eliminarEmpleado/accept", method = RequestMethod.GET)
	public String accept(Model model){
		empleadoService.borrar(cedula);
		
		model.addAttribute("mensaje", "El empleado fue borrado con exito");
		return "success";
	}
}
