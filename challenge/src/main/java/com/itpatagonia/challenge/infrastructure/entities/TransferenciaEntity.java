package com.itpatagonia.challenge.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "transferencia")
public class TransferenciaEntity {

   @Id
   @GeneratedValue
   private UUID idTransferencia;

   @Column(nullable = false, precision = 15, scale = 2)
   private BigDecimal importe;

   @ManyToOne
   @JoinColumn(name = "empresa_cuit", nullable = false)
   private EmpresaEntity empresaEntity;

   @Column(nullable = false, length = 30)
   private String cuentaDebito;

   @Column(nullable = false, length = 30)
   private String cuentaCredito;

   @Column(nullable = false)
   private LocalDateTime fechaTransferencia;

}
