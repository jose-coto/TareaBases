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
	private ProveedorService proveedorService;
	@Autowired
	private ProductoService productoService;
	
	private Producto productoModificar;

	@RequestMapping(value = "/modificarProducto/{id}/**", method = RequestMethod.GET)
	public String showForm(ProductoForm productoForm, Model model, @PathVariable String id,
			HttpServletRequest request) {

		int codigoProducto = Integer
				.parseInt(new AntPathMatcher().extractPathWithinPattern("/{id}/**", request.getRequestURI()));

		productoModificar = productoService.findProductByID(codigoProducto);
		System.out.println(productoModificar.getCodigoProducto());
		productoForm.setCodProducto(productoModificar.getCodigoProducto());
		productoForm.setNombre(productoModificar.getNombreProducto());
		productoForm.setPrecio(productoModificar.getPrecio());
		productoForm.setTipo(productoModificar.getTipo());
		productoForm.setCodProveedor(productoModificar.getProveedor().getCodigoProveedor());
		productoForm.setCantidadProductos(0);
		
		
		model.addAttribute("productoForm", productoForm);
		model.addAttribute("proveedores", proveedorService.findAllProveedors());
		return "modificarProducto";
	}

	@RequestMapping(value="/modificarProducto/salvar", method=RequestMethod.POST)
	public String save(@Valid ProductoForm productoForm, BindingResult bindingResult, Model model, @RequestParam Map<String, String> requestParams){
		
		boolean insertado;
		
		if(bindingResult.hasErrors()){
			model.addAttribute("productoForm", productoForm);
			model.addAttribute("proveedores", proveedorService.findAllProveedors());
			return "modificarProducto";
		}else{
			try {
				productoService.modificar(productoForm);
				insertado=true;
			} catch (SQLException e) {
				insertado=false;
			}
		}
		
		if(insertado){
			model.addAttribute("mensaje", "El producto fue modificado con exito");
			return "success";
		}else{
			model.addAttribute("mensaje", "No se puedo modificar el producto");
			return "error";
		}
	}

}
