package net.cfl.comerceshop.modelo;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import net.cfl.proshop.enums.PedidoEstado;  // Cambio el nombre del enum también si lo es necesario

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate pedidoFecha;  // Renombrado a pedidoFecha
    private BigDecimal montoTotal;

    @Enumerated(EnumType.STRING)
    private PedidoEstado pedidoEstado;  // Renombrado el enum a PedidoEstado

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoItem> pedidoItems = new HashSet<>();  // Renombrado a PedidoItem

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
