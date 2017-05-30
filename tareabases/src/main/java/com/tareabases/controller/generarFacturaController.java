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
import com.tareabases.bussines.FacturaService;
import com.tareabases.bussines.ProductoService;
import com.tareabases.domain.Factura;
import com.tareabases.form.FacturaForm;
import com.tareabases.form.ProveedorForm;

@Controller
public class generarFacturaController {
	@Autowired
	FacturaService facturaService;
	@Autowired
	ProductoService productoService;
	@Autowired
	EmpleadoService empleadoService;
	
	Factura factura;
	
	@RequestMapping(value="/generarFactura", method=RequestMethod.GET)
	public String showForm(Model model, FacturaForm facturaForm){
		model.addAttribute("productos", productoService.findAllProducts());
		model.addAttribute("empleados", empleadoService.findAllEmpleados());
		return "generarFactura";
	}
	
	@RequestMapping(value="/generarFactura/salvar", method=RequestMethod.POST)
	public String save(@Valid FacturaForm facturaForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			model.addAttribute("productos", productoService.findAllProducts());
			model.addAttribute("empleados", empleadoService.findAllEmpleados());
			return "generarFactura";
		}else{
			try {
				factura=facturaService.generarFactura(facturaForm);
				insertado=true;
			} catch (SQLException e) {
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("encabezado", "JOFRA S.A");
			model.addAttribute("subencabezado", "Su cadena farmaceutica de confianza");
			model.addAttribute("fecha", factura.getFecha());
			model.addAttribute("codFactura", factura.getCodFactura());
			model.addAttribute("codEmpleado", factura.getCedula());
			model.addAttribute("nombreEmpleado", factura.getNombre());
			model.addAttribute("cantidad", factura.getCantidadProductos());
			model.addAttribute("nombreProducto", factura.getNombreProducto());
			model.addAttribute("precio", factura.getPrecio());
			model.addAttribute("total", factura.getTotal());
			
			return "comprobanteFactura";
		}else{
			model.addAttribute("mensaje", "No se puedo registrar la compra");
			return "error";
		}
	}
}
