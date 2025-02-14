package com.itpatagonia.challenge.application.usecases;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.domain.repository.EmpresaRepositoryPort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ObtenerEmpresasAdheridasUltimoMesUseCaseTest {

   @Mock
   private EmpresaRepositoryPort empresaRepositoryPort;

   @InjectMocks
   private ObtenerEmpresasAdheridasUltimoMesUseCase obtenerEmpresasAdheridasUltimoMesUseCase;

   @Test
   @DisplayName("Obtener empresas que se adhirieron el ultimo mes exitosamente")
   public void FindByFechaAdhesionAfterTest() {
      LocalDate fechaInicio = LocalDate.now().minusMonths(1);

      Empresa empresa1 = new Empresa("CUIT123", "Empresa 1", LocalDate.now().minusMonths(2));
      Empresa empresa2 = new Empresa("CUIT456", "Empresa 2", LocalDate.now().minusMonths(1));
      Empresa empresa3 = new Empresa("CUIT789", "Empresa 3", LocalDate.now());

      List<Empresa> empresas = Arrays.asList(empresa2, empresa3);

      when(empresaRepositoryPort.findByFechaAdhesionAfter(fechaInicio)).thenReturn(empresas);

      List<Empresa> empresasAdheridas = obtenerEmpresasAdheridasUltimoMesUseCase.findByFechaAdhesionAfter();

      assertNotNull(empresasAdheridas);
      assertEquals(2, empresasAdheridas.size());
      assertTrue(empresasAdheridas.contains(empresa2));
      assertTrue(empresasAdheridas.contains(empresa3));

      verify(empresaRepositoryPort, times(1)).findByFechaAdhesionAfter(fechaInicio);
   }

}
