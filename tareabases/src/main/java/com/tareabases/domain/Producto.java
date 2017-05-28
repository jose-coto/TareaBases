package com.tareabases.domain;

public class Producto {

	
	private int codigoProducto;
	private String nombreProducto;
	private float precio;
	private String tipo;
	private Proveedor proveedor;
	
	public Producto() {
		
	}

	public Producto(int codigoProducto, String nombreProducto, float precio, String tipo, Proveedor proveedor) {
		super();
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.tipo = tipo;
		this.proveedor = proveedor;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}
