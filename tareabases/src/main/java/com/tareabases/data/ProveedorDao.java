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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tareabases.domain.Proveedor;
import com.tareabases.form.ProveedorForm;

@Repository
public class ProveedorDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallProveedorInsertar;
	private SimpleJdbcCall simpleJdbcCallProveedorBorrar;
	private SimpleJdbcCall simpleJdbcCallProveedorModificar;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		this.simpleJdbcCallProveedorBorrar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("eliminarProveedor")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("codigo", Types.INTEGER));

		this.simpleJdbcCallProveedorInsertar = new SimpleJdbcCall(dataSource).withCatalogName("dbo")
				.withProcedureName("insertarProveedor").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("nombre", Types.VARCHAR))
				.declareParameters(new SqlParameter("telefono", Types.VARCHAR));
		
		this.simpleJdbcCallProveedorModificar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("modificarProveedor")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("codigo", Types.INTEGER))
				.declareParameters(new SqlParameter("nombre", Types.VARCHAR))
				.declareParameters(new SqlParameter("telefono", Types.VARCHAR));
	}
	
	public ProveedorForm modificar(ProveedorForm proveedor) {

		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("codigo", proveedor.getCodProveedor())
				.addValue("nombre", proveedor.getNombre())
				.addValue("telefono", proveedor.getTelefono());
		
		simpleJdbcCallProveedorModificar.execute(parameterSource);
		
		return proveedor;
	}

	public void borrar(int codigoProveedor){
		System.out.println(codigoProveedor);
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("codigo", codigoProveedor);
		
		simpleJdbcCallProveedorBorrar.execute(parameterSource);
	}
	
	public ProveedorForm save(ProveedorForm proveedor) {
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("nombre", proveedor.getNombre())
				.addValue("telefono", proveedor.getTelefono());

		simpleJdbcCallProveedorInsertar.execute(parameterSource);

		return proveedor;
	}

	public List<Proveedor> findAllProveedors() {
		String sqlSelect = "SELECT codigo, nombre, telefono" + " from Proveedor" + " where nombre != 'No suministrado'";

		return jdbcTemplate.query(sqlSelect, new ProveedorExtractor());
	}

	private static final class ProveedorExtractor implements ResultSetExtractor<List<Proveedor>> {

		@Override
		public List<Proveedor> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Integer, Proveedor> map = new HashMap<Integer, Proveedor>();
			Proveedor proveedor = null;
			while (rs.next()) {
				Integer id = rs.getInt("codigo");
				proveedor = map.get(id);
				if (proveedor == null) { // new proveedor
					proveedor = new Proveedor();
					proveedor.setCodigoProveedor(id);
					proveedor.setNombre(rs.getString("nombre"));
					proveedor.setTelefono(rs.getString("telefono"));
					map.put(id, proveedor);
				} // if

			} // while
			return new ArrayList<Proveedor>(map.values());

		}
	}

	public Proveedor findProveedorByID(int codigoProveedor) {
		String sqlSelect= "SELECT p.codigo, p.nombre, p.telefono"
				+ " FROM Proveedor p"
				+ " where p.nombre != 'No suministrado' and p.codigo="+ codigoProveedor;
		
		List<Proveedor> proveedor= jdbcTemplate.query(sqlSelect, new ProveedorExtractor());
		
		if(proveedor.isEmpty()){
			return null;
		}else{
			return proveedor.get(0);
		}
	}
}
