package com.tareabases.form;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class ProveedorForm {
	private int codProveedor;
	@NotNull
	@Size(max=50)
	private String nombre;
	@Size(max=8)
	private String telefono;

	public ProveedorForm() {
		super();
	}
	
	public int getCodProveedor() {
		return codProveedor;
	}
	
	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
