package com.tareabases.data;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.assertj.core.error.ConditionAndGroupGenericParameterTypeShouldBeTheSame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tareabases.domain.Empleado;
import com.tareabases.domain.Factura;
import com.tareabases.form.FacturaForm;

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
				.declareParameters(new SqlParameter("cedulaEmpleado",Types.CHAR))
				.declareParameters(new SqlOutParameter("codFactura", Types.INTEGER));
	}
	
	public Factura generarFactura (FacturaForm facturaForm) throws SQLException{
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("cantidadProductos", facturaForm.getCantidadProductos())
				.addValue("codProducto", facturaForm.getCodProducto())
				.addValue("cedulaEmpleado", facturaForm.getCedulaEmpleado());
		
		
		Map<String, Object> outParameters = simpleJdbcCallFactura.execute(parameterSource);
		
		
		int codFactura=Integer.parseInt(outParameters.get("codFactura").toString());
		
		return findFacturaByCode(codFactura);
	}
	
	public Factura findFacturaByCode(int codigo){
		List<Factura> factura = new ArrayList<>();

		String sqlSelect = "select f.codigo,f.fecha,f.cantidadProductos,f.total,f.codProducto,"
				+ " p.nombre, p.precio, e.cedula, (e.nombre+e.apellidos) as nombreEmpleado "
				+ " from Factura f inner join GeneraFactura gf on f.codigo= gf.codigoFactura"
				+ " inner join Producto p on f.codProducto=p.codigo "
				+ " inner join Empleado e on gf.cedulaEmpleado=e.cedula"
				+ " where f.codigo="+codigo;
		
		jdbcTemplate.query(sqlSelect, new Object[] {},(rs, now) -> new Factura(rs.getInt("codigo"), 
				rs.getString("fecha"),rs.getInt("cantidadProductos"), rs.getInt("codProducto"),
				rs.getString("nombre"), rs.getFloat("precio"), rs.getFloat("total"), 
				rs.getString("cedula"), rs.getString("nombreEmpleado")))
				.forEach(entry -> factura.add(entry));

		if (factura.isEmpty()) {
			return null;
		} else {
			return factura.get(0);
		}
	}
}
