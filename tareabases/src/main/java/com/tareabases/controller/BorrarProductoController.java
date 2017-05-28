package com.tareabases.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tareabases.bussines.ProductoService;
import com.tareabases.domain.Producto;


@Controller
public class BorrarProductoController {

	@Autowired
	ProductoService productoService;
	
	private Producto productoBorrar;

	@RequestMapping(value="/eliminarProducto/{id}/**", method = RequestMethod.GET)
	public String remove(Model model, @PathVariable String id, HttpServletRequest request){
		int codigoProducto = Integer.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));
		
		productoBorrar = productoService.findProductByID(codigoProducto);
		model.addAttribute("productoBorrar", productoBorrar);
		return "eliminarProducto";
	}
	
	@RequestMapping(value="/eliminarProducto/accept", method = RequestMethod.GET)
	public String accept(Model model){
		
		productoService.borrar(productoBorrar.getCodigoProducto());
		
		model.addAttribute("mensaje", "El producto fue borrado con exito");
		return "success";
	}
}
