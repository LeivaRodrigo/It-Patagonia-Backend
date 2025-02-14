package com.itpatagonia.challenge.infrastructure.repositories;

import com.itpatagonia.challenge.infrastructure.entities.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpresaJpaRepository extends JpaRepository<EmpresaEntity, String> {

   List<EmpresaEntity> findByFechaAdhesionAfter(LocalDate fecha);

   @Query("SELECT DISTINCT t.empresaEntity FROM TransferenciaEntity t WHERE t.fechaTransferencia >= :fechaInicio")
   List<EmpresaEntity> findEmpresasConTransferenciasUltimoMes(LocalDateTime fechaInicio);

}