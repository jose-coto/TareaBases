package com.tareabases.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tareabases.data.ProductoDao;
import com.tareabases.data.ReporteEmpleadoDao;
import com.tareabases.domain.Empleado;

@Service
public class ReporteEmpleadoService {
	@Autowired
	private ReporteEmpleadoDao reporteEmpleadoDao;

	public Empleado reporte(String date1, String date2) {
		return reporteEmpleadoDao.reporte(date1, date2);
	}
	
	
	
	
}
