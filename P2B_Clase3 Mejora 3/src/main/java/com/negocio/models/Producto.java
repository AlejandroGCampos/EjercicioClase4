package com.negocio.models;

// ERROR 1: Atributos públicos (Mala práctica de encapsulamiento)
public class Producto {
    private  int id;
    private  String nombre;
    private double precio;
    public int stock;
    private int cantidad;

    // Getters y setters
    public int getid ()
    {
        return id;
    }
    public String getNombre()
    {
        return nombre;
    }
    public double getPrecio()
    {
        return precio;
    }
    public int getcantidad()
    {
        return cantidad;
    }
    public int getstock()
    {
        return stock;
    }

    // ERROR 2: Constructor sin validaciones
    public Producto(int id, String nombre, double precio, int stock, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock; // No valida si el stock es negativo
    }

    // 3. Mejora
    public void reducirStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a reducir debe ser positiva");
        }

        if (this.stock - cantidad < 0) {
            throw new IllegalStateException("No hay suficiente stock disponible");
        }

        this.stock -= cantidad;
    }

    // ERROR 4: Método con lógica incorrecta
    public boolean hayStock(int cantidad) {
        return stock >= cantidad; // Debería ser >= para permitir exactamente la cantidad
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
