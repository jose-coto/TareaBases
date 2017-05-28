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

import com.tareabases.domain.Empleado;
import com.tareabases.domain.Producto;
import com.tareabases.domain.Proveedor;
import com.tareabases.form.EmpleadoForm;
import com.tareabases.form.ProductoForm;

@Repository
public class EmpleadoDao {

	private JdbcTemplate jdbcTemplate;
	private DataSource datasource;
	private SimpleJdbcCall simpleJdbcCallEmpleadoInsertar;
	private SimpleJdbcCall simpleJdbcCallEmpleadoBorrar;
	private SimpleJdbcCall simpleJdbcCallEmpleadoModificar;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.datasource= dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCallEmpleadoInsertar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("insertarEmpleado")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("cedula", Types.CHAR))
				.declareParameters(new SqlParameter("seguroSocial", Types.CHAR))
				.declareParameters(new SqlParameter("nombre", Types.VARCHAR))
				.declareParameters(new SqlParameter("apellidos", Types.VARCHAR))
				.declareParameters(new SqlParameter("telefono", Types.CHAR))
				.declareParameters(new SqlParameter("tipo", Types.CHAR))
				.declareParameters(new SqlParameter("precioHora", Types.FLOAT))
				.declareParameters(new SqlParameter("licenciaLaboral", Types.VARCHAR));
		
		this.simpleJdbcCallEmpleadoBorrar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("eliminarEmpleado")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("cedula", Types.CHAR));
		
		this.simpleJdbcCallEmpleadoModificar = new SimpleJdbcCall(dataSource)
				.withCatalogName("dbo")
				.withProcedureName("modificarEmpleado")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("cedula", Types.CHAR))
				.declareParameters(new SqlParameter("seguroSocial", Types.CHAR))
				.declareParameters(new SqlParameter("nombre", Types.VARCHAR))
				.declareParameters(new SqlParameter("apellidos", Types.VARCHAR))
				.declareParameters(new SqlParameter("telefono", Types.CHAR))
				.declareParameters(new SqlParameter("tipo", Types.CHAR))
				.declareParameters(new SqlParameter("precioHora", Types.FLOAT))
				.declareParameters(new SqlParameter("licenciaLaboral", Types.VARCHAR));
	}
	
	public List<Empleado> findAllEmpleados(){
		String sqlSelect= "SELECT e.cedula, e.seguroSocial, e.nombre, e.apellidos, e.telefono, e.tipo, a.precioHora as precioHoraA,c.precioHora as precioHoraC, f.precioHora as precioHoraF, f.licenciaLaboral"
				+ " FROM Empleado e left join Administrador a on a.cedula=e.cedula"
				+ " left join Cajero c on c.cedula=e.cedula"
				+ " left join Farmaceutico f on f.cedula=e.cedula"
				+ " where e.nombre != 'No suministrado'";
		
		return jdbcTemplate.query(sqlSelect, new EmpleadoExtractor());
	}
	
	public Empleado findEmpleadoByCedula(String cedula){
		String sqlSelect= "SELECT e.cedula, e.seguroSocial, e.nombre, e.apellidos, e.telefono, e.tipo, a.precioHora as precioHoraA,c.precioHora as precioHoraC, f.precioHora as precioHoraF, f.licenciaLaboral"
				+ " FROM Empleado e left join Administrador a on a.cedula=e.cedula"
				+ " left join Cajero c on c.cedula=e.cedula"
				+ " left join Farmaceutico f on f.cedula=e.cedula"
				+ " where e.nombre != 'No suministrado' and e.cedula='"+cedula+"'";
		
		List<Empleado> empleado= jdbcTemplate.query(sqlSelect, new EmpleadoExtractor());
		System.out.println(empleado.size());
		if(empleado.isEmpty()){
			return null;
		}else{
			return empleado.get(0);
		}
	}
	
	public EmpleadoForm save(EmpleadoForm empleado) throws SQLException{
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("cedula", empleado.getCedula())
				.addValue("seguroSocial", empleado.getSeguroSocial())
				.addValue("nombre", empleado.getNombre())
				.addValue("apellidos", empleado.getApellidos())
				.addValue("telefono", empleado.getTelefono())
				.addValue("tipo", empleado.getTipo())
				.addValue("precioHora", empleado.getPrecioHora())
				.addValue("licenciaLaboral", empleado.getLicenciaLaboral());
		
		
		simpleJdbcCallEmpleadoInsertar.execute(parameterSource);
		
		return empleado;
	}
	
	public void edit(EmpleadoForm empleado) throws SQLException{
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("cedula", empleado.getCedula())
				.addValue("seguroSocial", empleado.getSeguroSocial())
				.addValue("nombre", empleado.getNombre())
				.addValue("apellidos", empleado.getApellidos())
				.addValue("telefono", empleado.getTelefono())
				.addValue("tipo", empleado.getTipo())
				.addValue("precioHora", empleado.getPrecioHora())
				.addValue("licenciaLaboral", empleado.getLicenciaLaboral());
		
		
		simpleJdbcCallEmpleadoModificar.execute(parameterSource);
	}
	
	public void borrar(String cedula){
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("cedula", cedula);
		
		simpleJdbcCallEmpleadoBorrar.execute(parameterSource);
	}
	
	private static final class EmpleadoExtractor implements ResultSetExtractor<List<Empleado>> {

		@Override
		public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<String, Empleado> map = new HashMap<String, Empleado>();
			Empleado empleado = null;
			while (rs.next()) {
				String id = rs.getString("cedula");
				empleado = map.get(id);
				if (empleado == null) { // new producto
					empleado= new Empleado();
					empleado.setCedula(id);
					empleado.setSeguroSocial(rs.getString("seguroSocial"));
					empleado.setNombre(rs.getString("nombre"));
					empleado.setApellidos(rs.getString("apellidos"));
					empleado.setTelefono(rs.getString("telefono"));
					empleado.setTipo(rs.getString("tipo"));
					if(empleado.getTipo().equals("C")){
						empleado.setPrecioHora(rs.getFloat("precioHoraC"));
						empleado.setLicenciaLaboral("N/A");
					}else if(empleado.getTipo().equals("A")){
						empleado.setPrecioHora(rs.getFloat("precioHoraA"));
						empleado.setLicenciaLaboral("N/A");
					}else if(empleado.getTipo().equals("F")){
						empleado.setPrecioHora(rs.getFloat("precioHoraF"));
						empleado.setLicenciaLaboral(rs.getString("licenciaLaboral"));
					}
					map.put(id, empleado);
				} // if
				
				// TODO Auto-generated method stub

			} // while
			return new ArrayList<Empleado>(map.values());

		} // PeliculasWithActoresExtractor
	}
}
