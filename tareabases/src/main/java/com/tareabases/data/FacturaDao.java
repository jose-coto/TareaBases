package com.tareabases.data;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class FacturaDao {
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallFactura;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCallFactura = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("generarFactura")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("cantidadProductos", Types.INTEGER))
				.declareParameters(new SqlParameter("codProducto",Types.INTEGER))
				.declareParameters(new SqlParameter("cedulaEmpleado",Types.CHAR));
	}
	
	public void generarFactura (int cantidadProductos, int codProducto, String cedulaEmpleado) {
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("cantidadProductos", cantidadProductos)
				.addValue("codProducto", codProducto)
				.addValue("cedulaEmpleado", cedulaEmpleado);
		
		simpleJdbcCallFactura.execute(parameterSource);
	}
}
