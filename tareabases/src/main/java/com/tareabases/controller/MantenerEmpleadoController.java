package com.tareabases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tareabases.bussines.EmpleadoService;
import com.tareabases.domain.Empleado;
import com.tareabases.domain.Producto;

@Controller
public class MantenerEmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	private List<Empleado> empleados;
	
	@RequestMapping(value="/mantenerEmpleados", method = RequestMethod.GET)
	public String iniciar(Model model){
		
		empleados= empleadoService.findAllEmpleados();
		model.addAttribute("empleados", empleados);
		return "mantenerEmpleados";
	}

}
