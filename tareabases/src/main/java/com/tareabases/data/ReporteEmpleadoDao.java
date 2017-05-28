package com.tareabases.data;

import java.sql.Types;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class ReporteEmpleadoDao {
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCallReporte;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		this.simpleJdbcCallReporte = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("reporteEmpleadoMejorPagado")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("fechaInicial", Types.DATE))
				.declareParameters(new SqlParameter("fechaFin",Types.DATE))
				.declareParameters(new SqlOutParameter("reporte", Types.VARCHAR));
	}
	
	public String reporte(String date1, String date2) {
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("fechaInicial", date1)
				.addValue("fechaFin", date2);
		Map<String,Object> mapa = null;
		try {
			mapa = simpleJdbcCallReporte.execute(parameterSource);
		} catch (Exception e) {
			return e.toString();
		}
		return mapa.get("reporte").toString();
	}
}
