package com.tareabases.bussines;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tareabases.data.FacturaDao;
import com.tareabases.domain.Factura;
import com.tareabases.form.FacturaForm;

@Service
public class FacturaService {
	
	@Autowired
	FacturaDao facturaDao;
	
	public Factura generarFactura (FacturaForm facturaForm) throws SQLException{
		return facturaDao.generarFactura(facturaForm);
	}
	
	public Factura findFacturaByCode(int codigo){
		return facturaDao.findFacturaByCode(codigo);
	}
}
