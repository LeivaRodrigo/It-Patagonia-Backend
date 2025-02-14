package com.itpatagonia.challenge.infrastructure.controllers;

import com.itpatagonia.challenge.application.usecases.ObtenerEmpresasAdheridasUltimoMesUseCase;
import com.itpatagonia.challenge.application.usecases.ObtenerEmpresasConTransferenciasUltimoMesUseCase;
import com.itpatagonia.challenge.application.usecases.AdherirEmpresaUseCase;
import com.itpatagonia.challenge.domain.model.Empresa;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class EmpresaController {

   private final ObtenerEmpresasAdheridasUltimoMesUseCase obtenerEmpresasAdheridasUltimoMesUseCase;
   private final ObtenerEmpresasConTransferenciasUltimoMesUseCase obtenerEmpresasConTransferenciasUltimoMesUseCase;
   private final AdherirEmpresaUseCase adherirEmpresaUseCase;

   @PostMapping("/adhesion")
   public ResponseEntity<Empresa> adherirEmpresa(
           @RequestParam @NotBlank(message = "El CUIT es obligatorio.") String cuit,
           @RequestParam @NotBlank(message = "La razón social es obligatoria.") String razonSocial
   ) {
      Empresa empresa = adherirEmpresaUseCase.adherir(cuit, razonSocial);
      return ResponseEntity.ok(empresa);
   }

   @GetMapping("/adheridas-ultimo-mes")
   public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
      return obtenerEmpresasAdheridasUltimoMesUseCase.findByFechaAdhesionAfter();
   }

   @GetMapping("/transferencias-ultimo-mes")
   public List<Empresa> obtenerEmpresasConTransferenciasUltimoMes() {
      return obtenerEmpresasConTransferenciasUltimoMesUseCase.findEmpresasConTransferenciasUltimoMes();
   }

}