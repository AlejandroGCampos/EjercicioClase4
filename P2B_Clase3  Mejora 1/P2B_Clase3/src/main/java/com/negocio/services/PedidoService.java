package com.negocio.services;

import com.negocio.models.Cliente;
import com.negocio.models.Pedido;
import com.negocio.models.Producto;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private List<Pedido> pedidos;
    private InventarioService inventarioService;
    private int contadorPedidos;

    public PedidoService(InventarioService inventarioService) {
        this.pedidos = new ArrayList<>();
        this.inventarioService = inventarioService;
        this.contadorPedidos = 1;
    }

    // ERROR 11: Inicialización incorrecta de variables
    public Pedido crearPedido(Cliente cliente) {
        Pedido pedido = new Pedido(contadorPedidos, cliente);
        contadorPedidos++; // Debería incrementar, no decrementar
        pedidos.add(pedido);
        return pedido;
    }

    // ERROR 12: Condición mal formulada en bucle
    public boolean agregarProductoAPedido(int pedidoId, int productoId, int cantidad) {
        Pedido pedido = buscarPedidoPorId(pedidoId);
        if (pedido == null) return false;

        Producto producto = inventarioService.buscarProductoPorId(productoId);
        if (producto == null) return false;

        //  Validar si el producto ya está en el pedido
        if (pedido.contieneProducto(productoId)) {
            System.out.println("El producto ya está agregado al pedido.");
            return false;
        }

        // ✔ Validar si hay stock suficiente antes de agregar todos los productos
        if (!producto.hayStock(cantidad)) {
            System.out.println("Stock insuficiente.");
            return false;
        }

        // Agregar el producto la cantidad de veces solicitada
        for (int i = 0; i < cantidad; i++) {
            if (inventarioService.venderProducto(productoId, 1)) {
                pedido.agregarProducto(producto);
            } else {
                return false;
            }
        }

        return true;
    }


    private Pedido buscarPedidoPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == pedido.getid()) {
                return pedido;
            }
        }
        return null;
    }

    public double calcularIngresosTotales() {
        double ingresos = 0;
        for (Pedido pedido : pedidos) {
            ingresos += pedido.getTotal();
        }
        return ingresos;
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidos;
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }
}
