package com.tareabases.data;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
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

import com.tareabases.domain.Empleado;

@Repository
public class ReporteEmpleadoDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Empleado reporte(String date1, String date2) {
		List<Empleado> empleado = new ArrayList<>();

		String sqlSelect = "exec reporteEmpleadoMejorPagado '" + date1 + "', '" + date2 + "'";
		Empleado e= new Empleado();
		
		jdbcTemplate
				.query(sqlSelect, new Object[] {},
						(rs, now) -> new Empleado(rs.getString("Cedula"), rs.getString("Seguro Social"),
								rs.getString("Nombre"), rs.getString("Telefono")))
				.forEach(entry -> empleado.add(entry));

		if (empleado.isEmpty()) {
			return null;
		} else {
			return empleado.get(0);
		}
	}
}
