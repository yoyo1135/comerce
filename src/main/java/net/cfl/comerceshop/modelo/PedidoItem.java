package net.cfl.comerceshop.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PedidoItem {  // Renombrado de OrdenItem a PedidoItem
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "pedido_id")  // Renombrado de "orden_id" a "pedido_id"
    private Pedido pedido;  // Renombrado de Orden a Pedido

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Constructor para inicializar los atributos sin necesidad de usar setters
    public PedidoItem(Pedido pedido, Producto producto, int cantidad, BigDecimal precio) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio != null ? precio : BigDecimal.ZERO; // Evitar precios nulos
    }

    // Método para calcular el precio total de un ítem
    public BigDecimal calcularPrecioTotal() {
        return precio.multiply(BigDecimal.valueOf(cantidad));
    }
}

