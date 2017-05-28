package com.tareabases.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductoForm {

	
	private int codProducto;
	@NotNull
	@Size(max=50)
	private String nombre;
	@NotNull
	private float precio;
	@NotNull
	private String tipo;
	@NotNull
	private int codProveedor;
	@NotNull
	private int cantidadProductos;
	
	public ProductoForm() {
		
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public int getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}

	public int getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	
	
	
}
