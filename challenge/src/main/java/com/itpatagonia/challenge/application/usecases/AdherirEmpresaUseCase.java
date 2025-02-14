package com.itpatagonia.challenge.application.usecases;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.domain.repository.EmpresaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdherirEmpresaUseCase {

   private final EmpresaRepositoryPort empresaRepositoryPort;

   public Empresa adherir(String cuit, String razonSocial) {
      if (cuit == null || cuit.isBlank() || razonSocial == null || razonSocial.isBlank()) {
         throw new IllegalArgumentException("Los datos no pueden estar vacíos.");
      }
      Empresa empresa = new Empresa(cuit, razonSocial, LocalDate.now());
      return empresaRepositoryPort.adherir(empresa);
   }

}