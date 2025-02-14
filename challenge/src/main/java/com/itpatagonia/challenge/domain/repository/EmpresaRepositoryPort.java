package com.itpatagonia.challenge.domain.repository;

import com.itpatagonia.challenge.domain.model.Empresa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpresaRepositoryPort {

   Empresa adherir(Empresa empresa);

   List<Empresa> findByFechaAdhesionAfter(LocalDate fechaAdhesion);

   List<Empresa> findEmpresasConTransferenciasUltimoMes(LocalDateTime fechaInicio);

}