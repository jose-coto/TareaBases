package com.tareabases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tareabases.bussines.ProveedorService;
import com.tareabases.domain.Proveedor;

@Controller
public class MantieneProveedor {
	
	@Autowired
	ProveedorService proveedorService;
	
	private List<Proveedor> proveedores;
	
	@RequestMapping(value="/mantenerProveedor", method = RequestMethod.GET)
	public String iniciar(Model model){
		
		proveedores = proveedorService.findAllProveedors();
		model.addAttribute("proveedores", proveedores);
		return "mantenerProveedores";
	}
	
	@RequestMapping(value={"/jofrasa","/index","/"})
	public String iniciar(){
		return "jofrasa";
	}
	
	@RequestMapping(value="/about")
	public String iniciar2(){
		return "about";
	}
	
}
