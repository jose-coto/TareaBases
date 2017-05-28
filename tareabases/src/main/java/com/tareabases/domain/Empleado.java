package com.tareabases.domain;

public class Empleado {
	
	private String cedula;
	private String seguroSocial;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String tipo;
	private float precioHora;
	private String licenciaLaboral;
	
	public Empleado() {
		
	}

	public Empleado(String cedula, String seguroSocial, String nombre, String apellidos, String telefono, String tipo,
			float precioHora, String licenciaLaboral) {
		super();
		this.cedula = cedula;
		this.seguroSocial = seguroSocial;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.tipo = tipo;
		this.precioHora = precioHora;
		this.licenciaLaboral = licenciaLaboral;
	}

	public Empleado(String cedula, String seguroSocial, String nombre,String telefono) {
		this.cedula = cedula;
		this.seguroSocial = seguroSocial;
		this.nombre = nombre;
		this.telefono = telefono;
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
