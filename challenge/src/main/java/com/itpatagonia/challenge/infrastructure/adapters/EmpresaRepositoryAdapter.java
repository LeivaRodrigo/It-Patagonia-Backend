package com.itpatagonia.challenge.infrastructure.adapters;

import com.itpatagonia.challenge.domain.repository.EmpresaRepositoryPort;
import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.infrastructure.entities.EmpresaEntity;
import com.itpatagonia.challenge.infrastructure.mapper.EmpresaMapper;
import com.itpatagonia.challenge.infrastructure.repositories.EmpresaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmpresaRepositoryAdapter implements EmpresaRepositoryPort {

   private final EmpresaJpaRepository empresaJpaRepository;

   @Override
   public Empresa adherir(Empresa empresa) {
      EmpresaEntity empresaEntity = new EmpresaEntity(
              empresa.getCuit(),
              empresa.getRazonSocial(),
              empresa.getFechaAdhesion()
      );

      EmpresaEntity empresaSaved = empresaJpaRepository.save(empresaEntity);

      return EmpresaMapper.fromEmpresaEntityToEmpresa(empresaSaved);
   }

   @Override
   public List<Empresa> findByFechaAdhesionAfter(LocalDate fecha) {
      return empresaJpaRepository.findByFechaAdhesionAfter(fecha)
              .stream()
              .map(EmpresaMapper::fromEmpresaEntityToEmpresa)
              .toList();
   }

   @Override
   public List<Empresa> findEmpresasConTransferenciasUltimoMes(LocalDateTime fechaInicio) {
      return empresaJpaRepository.findEmpresasConTransferenciasUltimoMes(fechaInicio)
              .stream()
              .map(EmpresaMapper::fromEmpresaEntityToEmpresa)
              .toList();
   }

}