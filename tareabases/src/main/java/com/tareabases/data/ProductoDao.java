package com.tareabases.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tareabases.domain.Producto;
import com.tareabases.domain.Proveedor;
import com.tareabases.form.ProductoForm;


@Repository
public class ProductoDao {
	
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallProductoBorrar;
	private SimpleJdbcCall simpleJdbcCallProductoInsertar;
	private SimpleJdbcCall simpleJdbcCallProductoModificar;
	private DataSource datasource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.datasource= dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		this.simpleJdbcCallProductoBorrar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("eliminarProducto")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("codProducto", Types.INTEGER));
		
		this.simpleJdbcCallProductoInsertar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("insertarProducto")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("nombre", Types.VARCHAR))
				.declareParameters(new SqlParameter("precio", Types.FLOAT))
				.declareParameters(new SqlParameter("tipo", Types.VARCHAR))
				.declareParameters(new SqlParameter("codProveedor", Types.INTEGER))
				.declareParameters(new SqlParameter("cantidadProductos", Types.INTEGER))
				.declareParameters(new SqlOutParameter("codProducto", Types.INTEGER));
		
		this.simpleJdbcCallProductoModificar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("modificarProducto")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("codProducto", Types.INTEGER))
				.declareParameters(new SqlParameter("nombre", Types.VARCHAR))
				.declareParameters(new SqlParameter("precio", Types.FLOAT))
				.declareParameters(new SqlParameter("tipo", Types.VARCHAR))
				.declareParameters(new SqlParameter("codProveedor", Types.INTEGER));
	}
	
	public List<Producto> findAllProducts(){
		String sqlSelect= "SELECT p.codigo, p.nombre, p.precio, p.tipo, p.codigoProveedor, pro.nombre as nombreProveedor, pro.telefono"
				+ " FROM Producto p left join Proveedor pro on p.codigoProveedor=pro.codigo"
				+ " where p.nombre != 'No suministrado'";
		
		return jdbcTemplate.query(sqlSelect, new ProductoExtractor());
	}
	
	public Producto findProductByID(int codigoProducto){
		String sqlSelect= "SELECT p.codigo, p.nombre, p.precio, p.tipo, p.codigoProveedor, pro.nombre as nombreProveedor, pro.telefono"
				+ " FROM Producto p left join Proveedor pro on p.codigoProveedor=pro.codigo"
				+ " where p.nombre != 'No suministrado' and p.codigo="+ codigoProducto;
		
		List<Producto> producto= jdbcTemplate.query(sqlSelect, new ProductoExtractor());
		
		if(producto.isEmpty()){
			return null;
		}else{
			return producto.get(0);
		}
	}
	
	public void borrar(int codigoProducto){
		System.out.println(codigoProducto);
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("codProducto", codigoProducto);
		
		simpleJdbcCallProductoBorrar.execute(parameterSource);
	}
	
	public ProductoForm save(ProductoForm producto) throws SQLException{
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("nombre", producto.getNombre())
				.addValue("precio", producto.getPrecio())
				.addValue("tipo", producto.getTipo())
				.addValue("codProveedor",producto.getCodProveedor())
				.addValue("cantidadProductos", producto.getCantidadProductos());
		
		simpleJdbcCallProductoInsertar.execute(parameterSource);
		
		//producto.setCodProducto(Integer.parseInt(outParameters.get("codProducto").toString()));
		return producto;
	}
	

	public void modificar(ProductoForm producto){
		
		System.out.println(producto.getCodProducto());
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("codProducto", producto.getCodProducto())
				.addValue("nombre", producto.getNombre())
				.addValue("precio", producto.getPrecio())
				.addValue("tipo", producto.getTipo())
				.addValue("codProveedor",producto.getCodProveedor());
		
		simpleJdbcCallProductoModificar.execute(parameterSource);
	}
	
	private static final class ProductoExtractor implements ResultSetExtractor<List<Producto>> {

		@Override
		public List<Producto> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Producto> map = new HashMap<Integer, Producto>();
			Producto producto = null;
			while (rs.next()) {
				Integer id = rs.getInt("codigo");
				producto = map.get(id);
				if (producto == null) { // new producto
					producto= new Producto();
					producto.setCodigoProducto(id);
					producto.setNombreProducto(rs.getString("nombre"));
					producto.setPrecio(rs.getFloat("precio"));
					producto.setTipo(rs.getString("tipo"));
					map.put(id, producto);
				} // if
				int proveedorId = rs.getInt("codigoProveedor");
				if (proveedorId > 0) {
					Proveedor proveedor = new Proveedor();
					proveedor.setCodigoProveedor(proveedorId);
					proveedor.setNombre(rs.getString("nombreProveedor"));
					proveedor.setTelefono(rs.getString("telefono"));
					producto.setProveedor(proveedor);
				} // if
				// TODO Auto-generated method stub

			} // while
			return new ArrayList<Producto>(map.values());

		} // PeliculasWithActoresExtractor
	}
}
