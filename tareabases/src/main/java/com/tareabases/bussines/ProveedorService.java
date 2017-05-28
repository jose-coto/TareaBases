package com.tareabases.bussines;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tareabases.data.ProveedorDao;
import com.tareabases.domain.Proveedor;
import com.tareabases.form.ProveedorForm;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorDao provvedorDao;
	
	
	public List<Proveedor> findAllProveedors(){
		return provvedorDao.findAllProveedors();
	}
	
	public ProveedorForm save(ProveedorForm proveedor) throws SQLException{
		return provvedorDao.save(proveedor);
	}

	public Proveedor findProveedorByID(int codigoProveedor) {
		return provvedorDao.findProveedorByID(codigoProveedor);
	}

	public void borrar(int codigoProveedor) {
		provvedorDao.borrar(codigoProveedor);
	}

	public ProveedorForm modificar(ProveedorForm proveedor) throws SQLException{
		return provvedorDao.modificar(proveedor);
	}

}
