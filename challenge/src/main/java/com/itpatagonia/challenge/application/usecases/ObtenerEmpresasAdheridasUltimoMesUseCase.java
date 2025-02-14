package com.itpatagonia.challenge.application.usecases;

import com.itpatagonia.challenge.domain.repository.EmpresaRepositoryPort;
import com.itpatagonia.challenge.domain.model.Empresa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ObtenerEmpresasAdheridasUltimoMesUseCase {

   private final EmpresaRepositoryPort empresaRepositoryPort;

   public List<Empresa> findByFechaAdhesionAfter() {
      LocalDate fechaInicio = LocalDate.now().minusMonths(1);
      return empresaRepositoryPort.findByFechaAdhesionAfter(fechaInicio);
   }

}