package com.tareabases.domain;

public class Factura {

	private int codFactura;
	private String fecha;
	private int cantidadProductos;
	private int codProducto;
	private String nombreProducto;
	private float precio;
	private float total;
	private String cedula;
	private String nombre;
	
	public Factura() {
		super();
	}

	
	public Factura(int codFactura, String fecha, int cantidadProductos, int codProducto, String nombreProducto,
			float precio, float total, String cedula, String nombre) {
		super();
		this.codFactura = codFactura;
		this.fecha = fecha;
		this.cantidadProductos = cantidadProductos;
		this.codProducto = codProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.total = total;
		this.cedula = cedula;
		this.nombre = nombre;
	}


	public int getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(int codFactura) {
		this.codFactura = codFactura;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
