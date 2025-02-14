package com.itpatagonia.challenge.application.usecases;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.domain.repository.EmpresaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ObtenerEmpresasConTransferenciasUltimoMesUseCase {

   private final EmpresaRepositoryPort empresaRepositoryPort;

   public List<Empresa> findEmpresasConTransferenciasUltimoMes() {
      LocalDateTime fechaInicio = LocalDateTime.now().minusMonths(1);
      return empresaRepositoryPort.findEmpresasConTransferenciasUltimoMes(fechaInicio);
   }

}