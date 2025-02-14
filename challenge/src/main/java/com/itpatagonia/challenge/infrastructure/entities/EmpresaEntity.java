package com.itpatagonia.challenge.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empresa")
public class EmpresaEntity {

   @Id
   private String cuit;

   @Column(nullable = false)
   private String razonSocial;

   @Column(nullable = false)
   private LocalDate fechaAdhesion;

}