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

import com.tareabases.bussines.ProductoService;
import com.tareabases.bussines.ProveedorService;
import com.tareabases.domain.Producto;
import com.tareabases.form.ProductoForm;

@Controller
public class ModificarProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	private ProductoForm productoModificar;
	
	@RequestMapping(value="/modificarProducto/{id}/**", method=RequestMethod.GET)
	public String showForm(ProductoForm productoForm, Model model, @PathVariable String id, HttpServletRequest request){
		
		int codigoProducto = Integer.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));
		
		
		Producto producto = productoService.findProductByID(codigoProducto);
		productoForm.setCodProducto(producto.getCodigoProducto());
		productoForm.setNombre(producto.getNombreProducto());
		productoForm.setPrecio(producto.getPrecio());
		productoForm.setTipo(producto.getTipo());
		productoForm.setCodProveedor(producto.getCodigoProducto());
		productoForm.setCantidadProductos(0);
		
		//model.addAttribute("productoForm", productoModificar);
		model.addAttribute("proveedores", proveedorService.findAllProveedors());
		
		return "modificarProducto";
	}
	
	@RequestMapping(value="/modifcarProducto/salvar", method=RequestMethod.POST)
	public String save(@Valid ProductoForm productoForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean modificado;
		
		if(bindingResult.hasErrors()){
			//model.addAttribute("productoForm", productoModificar);
			model.addAttribute("proveedores", proveedorService.findAllProveedors());
			return "modificarProducto";
		}else{
			productoService.modificar(productoForm);
			modificado=true;
		}
		
		if(modificado){
			model.addAttribute("mensaje", "El producto fue modificado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo modficar el producto");
			return "error";
		}
	}
}
