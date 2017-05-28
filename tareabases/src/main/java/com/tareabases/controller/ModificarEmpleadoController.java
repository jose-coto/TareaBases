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

import com.tareabases.bussines.EmpleadoService;
import com.tareabases.bussines.ProductoService;
import com.tareabases.bussines.ProveedorService;
import com.tareabases.domain.Empleado;
import com.tareabases.domain.Producto;
import com.tareabases.form.EmpleadoForm;
import com.tareabases.form.ProductoForm;

@Controller
public class ModificarEmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;
	
	private Empleado empleadoModificar;

	@RequestMapping(value = "/modificarEmpleado/{id}/**", method = RequestMethod.GET)
	public String showForm(EmpleadoForm empleadoForm, Model model, HttpServletRequest request) {

		String cedulaBorrar = new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI());
		
		empleadoModificar = empleadoService.findEmpleadoByCedula(cedulaBorrar);
		if (empleadoModificar != null){
			empleadoForm.setCedula(cedulaBorrar);
			empleadoForm.setNombre(empleadoModificar.getNombre());
			empleadoForm.setSeguroSocial(empleadoModificar.getSeguroSocial());
			empleadoForm.setApellidos(empleadoModificar.getApellidos());
			empleadoForm.setTelefono(empleadoModificar.getTelefono());
			empleadoForm.setTipo(empleadoModificar.getTipo());
			empleadoForm.setPrecioHora(empleadoModificar.getPrecioHora());
			empleadoForm.setLicenciaLaboral(empleadoModificar.getLicenciaLaboral());
		}
		model.addAttribute("empleadoForm", empleadoForm);
		return "modificarEmpleado";
	}

	@RequestMapping(value="/modificarEmpleado/salvar", method=RequestMethod.POST)
	public String save(@Valid EmpleadoForm empleadoForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			model.addAttribute("empleadoForm", empleadoForm);
			return "modificarEmpleado";
		}else{
			try {
				empleadoService.modificar(empleadoForm);
				insertado=true;
			} catch (SQLException e) {
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("mensaje", "El empleado fue modificado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo modificar el empleado");
			return "error";
		}
	}
}
