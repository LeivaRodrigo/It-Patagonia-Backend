package com.itpatagonia.challenge.infrastructure.mapper;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.infrastructure.entities.EmpresaEntity;

public class EmpresaMapper {

   public static Empresa fromEmpresaEntityToEmpresa(EmpresaEntity empresaEntity) {
      return new Empresa(
              empresaEntity.getCuit(),
              empresaEntity.getRazonSocial(),
              empresaEntity.getFechaAdhesion()
      );
   }

}