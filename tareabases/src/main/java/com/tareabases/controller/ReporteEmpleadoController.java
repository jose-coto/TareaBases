package com.tareabases.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tareabases.bussines.ReporteEmpleadoService;

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
		String reporte = reporteEmpleadoService.reporte(date1, date2);
		model.addAttribute("mensaje", reporte);
		
		return "success";
	}
	
}
