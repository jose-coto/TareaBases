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

import com.tareabases.bussines.ProveedorService;
import com.tareabases.form.ProductoForm;
import com.tareabases.form.ProveedorForm;

@Controller
public class InsertarProveedorController {
	
	@Autowired
	ProveedorService proveedorService;
	
	@RequestMapping(value="/addProveedor", method=RequestMethod.GET)
	public String showForm(ProveedorForm proveedorForm, Model model) {
		model.addAttribute("proveedorForm", proveedorForm);
		return "addProveedor";
	}
	
	@RequestMapping(value="/addProveedor/salvar", method=RequestMethod.POST)
	public String save(@Valid ProveedorForm proveedorForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			model.addAttribute("proveedorForm", proveedorForm);
			return "addProveedor";
		}else{
			try {
				proveedorService.save(proveedorForm);
				insertado=true;
			} catch (SQLException e) {
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("mensaje", "El proveedor fue insertado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo insertar el proveedor");
			return "error";
		}
	}
	
	
}
