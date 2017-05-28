package com.tareabases.domain;

public class Proveedor {

	private int codigoProveedor;
	private String nombre;
	private String telefono;
	
	
	
	public Proveedor() {
		
	}
	public Proveedor(int codigoProveedor, String nombre, String telefono) {
		super();
		this.codigoProveedor = codigoProveedor;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	public int getCodigoProveedor() {
		return codigoProveedor;
	}
	public void setCodigoProveedor(int codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
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
