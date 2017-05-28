package com.tareabases.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tareabases.bussines.ProveedorService;
import com.tareabases.domain.Proveedor;

@Controller
public class BorrarProveedorController {
	@Autowired
	ProveedorService proveedorService;
	
	private Proveedor proveedorBorrar;
	
	@RequestMapping(value="/eliminarProveedor/{id}/**", method = RequestMethod.GET)
	public String remove(Model model, @PathVariable String id, HttpServletRequest request){
		int codigoProveedor = Integer.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));
		
		proveedorBorrar = proveedorService.findProveedorByID(codigoProveedor);
		model.addAttribute("proveedorBorrar", proveedorBorrar);
		return "eliminarProveedor";
	}
	
	@RequestMapping(value="/eliminarProveedor/accept", method = RequestMethod.GET)
	public String accept(Model model){
		
		proveedorService.borrar(proveedorBorrar.getCodigoProveedor());
		
		model.addAttribute("mensaje", "El proveedor fue borrado con exito");
		return "success";
	}
}
