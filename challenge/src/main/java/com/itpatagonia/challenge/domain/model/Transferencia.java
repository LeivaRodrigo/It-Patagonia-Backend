package com.itpatagonia.challenge.domain.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Transferencia {

   private UUID idtransferencia;

   private BigDecimal importe;

   private Empresa empresa;

   private String cuentaDebito;

   private String cuentaCredito;

   private LocalDateTime fechaTransferencia;

}