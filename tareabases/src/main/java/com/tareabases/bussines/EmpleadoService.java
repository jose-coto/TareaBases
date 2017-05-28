package com.tareabases.bussines;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tareabases.data.EmpleadoDao;
import com.tareabases.domain.Empleado;
import com.tareabases.form.EmpleadoForm;

@Service
public class EmpleadoService {
	
	@Autowired
	private EmpleadoDao empleadoDao;
	
	public List<Empleado> findAllEmpleados(){
		return empleadoDao.findAllEmpleados();
	}

	public EmpleadoForm save(EmpleadoForm empleado) throws SQLException{
		return empleadoDao.save(empleado);
	}
	
	public Empleado findEmpleadoByCedula(String cedula){
		return empleadoDao.findEmpleadoByCedula(cedula);
	}
	
	public void borrar(String cedula){
		empleadoDao.borrar(cedula);
	}
}
