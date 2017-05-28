package com.tareabases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tareabases.bussines.ProductoService;
import com.tareabases.domain.Producto;

@Controller
public class MantieneProductoController {
	
	@Autowired
	ProductoService productoService;
	
	private List<Producto> productos;
	
	@RequestMapping(value="/mantenerProductos", method = RequestMethod.GET)
	public String iniciar(Model model){
		
		productos= productoService.findAllProducts();
		model.addAttribute("productos", productos);
		return "mantenerProductos";
	}

}
