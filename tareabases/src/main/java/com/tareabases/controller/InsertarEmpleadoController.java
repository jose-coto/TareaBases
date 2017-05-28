package com.tareabases.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tareabases.bussines.EmpleadoService;
import com.tareabases.form.EmpleadoForm;
import com.tareabases.form.ProductoForm;

@Controller
public class InsertarEmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	
	
	@RequestMapping(value="/addEmpleado", method=RequestMethod.GET)
	public String showForm(EmpleadoForm EmpleadoForm, Model model){
		//model.addAttribute("empleados", empleadoService.findAllEmpleados());
		return "addEmpleado";
	}
	
	@RequestMapping(value="/addEmpleado/salvar", method=RequestMethod.POST)
	public String save(@Valid EmpleadoForm empleadoForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			return "addEmpleado";
		}else{
			try {
				empleadoService.save(empleadoForm);
				insertado=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("mensaje", "El empleado fue insertado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo insertar el empleado");
			return "error";
		}
	}
}
