package com.itpatagonia.challenge.infrastructure.mapper;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.infrastructure.entities.EmpresaEntity;
import com.itpatagonia.challenge.infrastructure.mapper.EmpresaMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmpresaMapperTest {

   @Test
   @DisplayName("Mappea de Entidad a Empresa exitosamente")
   void fromEmpresaEntityToEmpresaTest() {
      EmpresaEntity empresaEntity = new EmpresaEntity("30-12345678-9", "Empresa Test", LocalDate.of(2024, 2, 14));

      Empresa empresa = EmpresaMapper.fromEmpresaEntityToEmpresa(empresaEntity);

      assertNotNull(empresa);
      assertEquals("30-12345678-9", empresa.getCuit());
      assertEquals("Empresa Test", empresa.getRazonSocial());
      assertEquals(LocalDate.of(2024, 2, 14), empresa.getFechaAdhesion());
   }
}
