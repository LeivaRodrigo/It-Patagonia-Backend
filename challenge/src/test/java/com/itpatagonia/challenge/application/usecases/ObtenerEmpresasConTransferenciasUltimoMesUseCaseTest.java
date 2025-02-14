package com.itpatagonia.challenge.application.usecases;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.domain.repository.EmpresaRepositoryPort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ObtenerEmpresasConTransferenciasUltimoMesUseCaseTest {

   @Mock
   private EmpresaRepositoryPort empresaRepositoryPort;

   @InjectMocks
   private ObtenerEmpresasConTransferenciasUltimoMesUseCase obtenerEmpresasConTransferenciasUltimoMesUseCase;

   @Test
   @DisplayName("Busca empresas con trnasferencias de forma correcta")
   void findEmpresasConTransferenciasUltimoMesTest() {
      LocalDateTime fechaEsperada = LocalDateTime.now().minusMonths(1);
      List<Empresa> empresasMock = List.of(
              new Empresa("30-12345678-9", "Empresa A", fechaEsperada.toLocalDate()),
              new Empresa("30-87654321-0", "Empresa B", fechaEsperada.toLocalDate())
      );

      when(empresaRepositoryPort.findEmpresasConTransferenciasUltimoMes(any(LocalDateTime.class)))
              .thenReturn(empresasMock);

      List<Empresa> empresas = obtenerEmpresasConTransferenciasUltimoMesUseCase.findEmpresasConTransferenciasUltimoMes();

      assertNotNull(empresas);
      assertEquals(2, empresas.size());
      verify(empresaRepositoryPort, times(1)).findEmpresasConTransferenciasUltimoMes(any(LocalDateTime.class));
   }

   @Test
   @DisplayName("Busca empresas con trnasferencias, no hay resultados")
   void findEmpresasConTransferenciasUltimoMesSinResultsTest() {
      when(empresaRepositoryPort.findEmpresasConTransferenciasUltimoMes(any(LocalDateTime.class)))
              .thenReturn(List.of());

      List<Empresa> empresas = obtenerEmpresasConTransferenciasUltimoMesUseCase.findEmpresasConTransferenciasUltimoMes();

      assertNotNull(empresas);
      assertTrue(empresas.isEmpty());
      verify(empresaRepositoryPort, times(1)).findEmpresasConTransferenciasUltimoMes(any(LocalDateTime.class));
   }

}
