package com.tareabases.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmpleadoForm {

	
	@NotNull
	@Size(min=9,max=9)
	private String cedula;
	@NotNull
	@Size(min=15,max=15)
	private String seguroSocial;
	@NotNull
	@Size(max=50)
	private String nombre;
	@NotNull
	@Size(max=75)
	private String apellidos;
	@Size(max=8)
	private String telefono;
	@NotNull
	private String tipo;
	@NotNull
	private float precioHora;

	private String licenciaLaboral;

	
	
	public EmpleadoForm() {
	}

	public EmpleadoForm(String cedula, String seguroSocial, String nombre, String apellidos, String telefono,
			String tipo, float precioHora, String licenciaLaboral) {

		this.cedula = cedula;
		this.seguroSocial = seguroSocial;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.tipo = tipo;
		this.precioHora = precioHora;
		this.licenciaLaboral = licenciaLaboral;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getSeguroSocial() {
		return seguroSocial;
	}

	public void setSeguroSocial(String seguroSocial) {
		this.seguroSocial = seguroSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(float precioHora) {
		this.precioHora = precioHora;
	}

	public String getLicenciaLaboral() {
		return licenciaLaboral;
	}

	public void setLicenciaLaboral(String licenciaLaboral) {
		this.licenciaLaboral = licenciaLaboral;
	}
	
	
}
