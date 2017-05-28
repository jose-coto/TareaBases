package com.tareabases.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tareabases.bussines.FacturaService;
import com.tareabases.bussines.ProductoService;

@Controller
public class generarFacturaController {
	@Autowired
	FacturaService facturaService;
	
	@Autowired
	ProductoService productoService;
	
	@RequestMapping(value="/generarFactura", method=RequestMethod.GET)
	public String showForm(Model model){
		model.addAttribute("productos", productoService.findAllProducts());
		model.addAttribute("Empleados", null);
		return "generarFactura";
	}
}
