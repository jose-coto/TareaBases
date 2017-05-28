package com.tareabases.bussines;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tareabases.data.ProductoDao;
import com.tareabases.domain.Producto;
import com.tareabases.form.ProductoForm;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoDao productoDao;
	
	public List<Producto> findAllProducts(){
		return productoDao.findAllProducts();
	}
	
	public Producto findProductByID(int codigoProducto){
		return productoDao.findProductByID(codigoProducto);
	}
	
	public void borrar(int codigoProducto){
		productoDao.borrar(codigoProducto);
	}
	
	public ProductoForm save(ProductoForm producto) throws SQLException{
		return productoDao.save(producto);
	}

	public void modificar(ProductoForm producto){
		productoDao.modificar(producto);
	}
}
