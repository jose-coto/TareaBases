package com.tareabases.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tareabases.bussines.ProveedorService;
import com.tareabases.domain.Proveedor;
import com.tareabases.form.ProveedorForm;

@Controller
public class ModificarProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	private Proveedor proveedorModificar;

	@RequestMapping(value = "/modificarProveedor/{id}/**", method = RequestMethod.GET)
	public String showForm(ProveedorForm proveedorForm, Model model, @PathVariable String id,
			HttpServletRequest request) {

		int codigoProveedor = Integer
				.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));

		proveedorModificar = proveedorService.findProveedorByID(codigoProveedor);
		//model.addAttribute("proveedorModificar", proveedorModificar);
		
		proveedorForm.setCodProveedor(proveedorModificar.getCodigoProveedor());
		proveedorForm.setNombre(proveedorModificar.getNombre());
		proveedorForm.setTelefono(proveedorModificar.getTelefono());
		
		
		model.addAttribute("proveedorForm", proveedorForm);

		return "modificarProveedor";
	}

	@RequestMapping(value="/modificarProveedor/salvar", method=RequestMethod.POST)
	public String save(@Valid ProveedorForm proveedorForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			//model.addAttribute("proveedorModificar", proveedorModificar);
			model.addAttribute("proveedorForm", proveedorForm);
			return "modificarProveedor";
		}else{
			try {
				proveedorService.modificar(proveedorForm);
				insertado=true;
			} catch (SQLException e) {
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("mensaje", "El proveedor fue modificado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo modificar el proveedor");
			return "error";
		}
	}

}
