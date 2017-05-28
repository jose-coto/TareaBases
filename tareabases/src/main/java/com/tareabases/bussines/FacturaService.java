package com.tareabases.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tareabases.data.FacturaDao;

@Service
public class FacturaService {
	
	@Autowired
	FacturaDao facturaDao;
	
	public void generarFactura (int cantidadProductos, int codProducto, String cedulaEmpleado) {
		facturaDao.generarFactura(cantidadProductos, codProducto, cedulaEmpleado);
	}
}
