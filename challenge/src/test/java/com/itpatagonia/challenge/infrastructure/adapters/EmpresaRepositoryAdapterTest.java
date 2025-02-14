package com.itpatagonia.challenge.infrastructure.adapters;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.infrastructure.entities.EmpresaEntity;
import com.itpatagonia.challenge.infrastructure.repositories.EmpresaJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmpresaRepositoryAdapterTest {

   @Mock
   private EmpresaJpaRepository empresaJpaRepository;

   @InjectMocks
   private EmpresaRepositoryAdapter empresaRepositoryAdapter;

   @Test
   @DisplayName("Guarda y retorna una empresa exitosamente")
   void adherirTest() {
      Empresa empresaMock = new Empresa("30-12345678-9", "Empresa Test", LocalDate.now());
      EmpresaEntity empresaEntity = new EmpresaEntity(empresaMock.getCuit(), empresaMock.getRazonSocial(), empresaMock.getFechaAdhesion());

      when(empresaJpaRepository.save(any(EmpresaEntity.class))).thenReturn(empresaEntity);

      Empresa empresa = empresaRepositoryAdapter.adherir(empresaMock);

      assertNotNull(empresa);
      assertEquals(empresaMock.getCuit(), empresa.getCuit());
      verify(empresaJpaRepository, times(1)).save(any(EmpresaEntity.class));
   }

   @Test
   @DisplayName("Busca y retorna las empresas adherias exitosamente")
   void findByFechaAdhesionAfterTest() {
      LocalDate fecha = LocalDate.now().minusMonths(1);
      List<EmpresaEntity> empresasMock = List.of(
              new EmpresaEntity("30-12345678-9", "Empresa A", fecha.plusDays(5)),
              new EmpresaEntity("30-87654321-0", "Empresa B", fecha.plusDays(10))
      );

      when(empresaJpaRepository.findByFechaAdhesionAfter(fecha)).thenReturn(empresasMock);

      List<Empresa> empresas = empresaRepositoryAdapter.findByFechaAdhesionAfter(fecha);

      assertNotNull(empresas);
      assertEquals(2, empresas.size());
      verify(empresaJpaRepository, times(1)).findByFechaAdhesionAfter(fecha);
   }

   @Test
   @DisplayName("Busca y retorna las empresas con transferencias exitosamente")
   void findEmpresasConTransferenciasUltimoMesTest() {
      LocalDateTime fechaInicio = LocalDateTime.now().minusMonths(1);
      List<EmpresaEntity> empresasMock = List.of(
              new EmpresaEntity("30-12345678-9", "Empresa A", LocalDate.now().minusDays(10)),
              new EmpresaEntity("30-87654321-0", "Empresa B", LocalDate.now().minusDays(5))
      );

      when(empresaJpaRepository.findEmpresasConTransferenciasUltimoMes(fechaInicio)).thenReturn(empresasMock);

      List<Empresa> empresas = empresaRepositoryAdapter.findEmpresasConTransferenciasUltimoMes(fechaInicio);

      assertNotNull(empresas);
      assertEquals(2, empresas.size());
      verify(empresaJpaRepository, times(1)).findEmpresasConTransferenciasUltimoMes(fechaInicio);
   }

}
