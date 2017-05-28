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

import com.tareabases.bussines.ProductoService;
import com.tareabases.bussines.ProveedorService;
import com.tareabases.form.ProductoForm;


@Controller
public class InsertarProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	
	@RequestMapping(value="/addProducto", method=RequestMethod.GET)
	public String showForm(ProductoForm productoForm, Model model){
		model.addAttribute("proveedores", proveedorService.findAllProveedors());
		return "addProducto";
	}
	
	@RequestMapping(value="/addProducto/salvar", method=RequestMethod.POST)
	public String save(@Valid ProductoForm productoForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			model.addAttribute("proveedores", proveedorService.findAllProveedors());
			return "addProduct";
		}else{
			try {
				productoService.save(productoForm);
				insertado=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("mensaje", "El producto fue insertado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo insertar el producto");
			return "error";
		}
	}
}
