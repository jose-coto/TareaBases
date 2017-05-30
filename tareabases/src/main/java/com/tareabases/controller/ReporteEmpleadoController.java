package com.tareabases.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tareabases.bussines.ReporteEmpleadoService;
import com.tareabases.domain.Empleado;

@Controller
public class ReporteEmpleadoController {
	@Autowired
	ReporteEmpleadoService reporteEmpleadoService;
	
	@RequestMapping(value="/reporteEmpleado", method=RequestMethod.GET)
	public String reporte(){
		return "reporte";
	}
	
	@RequestMapping(value="/reporteEmpleado/go", method=RequestMethod.POST)
	public String reporte(Model model, @RequestParam Map<String, String> requestParams){
		String date1 = requestParams.get("date1");
		String date2 = requestParams.get("date2");
		Empleado e = reporteEmpleadoService.reporte(date1, date2);
		
		model.addAttribute("encabezado","El empleado(a) mejor pagado es");
		model.addAttribute("cedula",e.getCedula());
		model.addAttribute("seguroSocial",e.getSeguroSocial());
		model.addAttribute("nombre",e.getNombre());
		model.addAttribute("telefono",e.getTelefono());
		return "reporteEmpleadoMejorPagado";
	}
	
}
