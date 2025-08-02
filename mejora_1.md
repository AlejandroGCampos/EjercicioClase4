# Mejora 1

Mejora #[1]:1

Ubicación: PedidoService en agregarProductoAPedido

Descripción del cambio: Cree una validación para agregarProductoAPedido que verifica si el producto ya fue agregado antes de continuar con el bucle y cree un método para evitar que se agregue el mismo producto más de una vez a un pedido

Justificación: Evita duplicar productos con el mismo nombre mejorando la integridad de los datos

Mejora #[1]:2

Ubicación: Clase Pedido, constructor public Pedido(int id, Cliente cliente)

Descripción del cambio: Se establece automáticamente la fecha y hora del pedido con this.fecha = LocalDateTime.now();

Justificación: Permite registrar el momento exacto en que se crea un pedido
