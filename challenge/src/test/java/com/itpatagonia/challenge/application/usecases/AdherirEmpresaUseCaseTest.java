package com.itpatagonia.challenge.application.usecases;

import com.itpatagonia.challenge.domain.model.Empresa;
import com.itpatagonia.challenge.domain.repository.EmpresaRepositoryPort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdherirEmpresaUseCaseTest {

   private final static String CUIT = "30-12345678-9";
   private final static String RAZON_SOCIAL = "Empresa Test";

   @Mock
   private EmpresaRepositoryPort empresaRepositoryPort;

   @InjectMocks
   private AdherirEmpresaUseCase adherirEmpresaUseCase;

   @Test
   @DisplayName("Adhiere una empresa de forma correcta")
   void adherirEmpresaTest() {
      String cuit = CUIT;
      String razonSocial = RAZON_SOCIAL;
      Empresa empresaEsperada = new Empresa(cuit, razonSocial, LocalDate.now());

      when(empresaRepositoryPort.adherir(any(Empresa.class))).thenReturn(empresaEsperada);

      Empresa empresa = adherirEmpresaUseCase.adherir(cuit, razonSocial);

      assertNotNull(empresa);
      assertEquals(cuit, empresa.getCuit());
      assertEquals(razonSocial, empresa.getRazonSocial());
      verify(empresaRepositoryPort, times(1)).adherir(any(Empresa.class));
   }

   @Test
   @DisplayName("Error al adherir empresa, CUIT nulo")
   void adherirEmpresaCuitNuloTest() {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
         adherirEmpresaUseCase.adherir(null, RAZON_SOCIAL);
      });

      assertEquals("Los datos no pueden estar vacíos.", exception.getMessage());
   }

   @Test
   @DisplayName("Error al adherir empresa, Razon Social nulo")
   void adherirEmpresaRazonSocialNuloTest() {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
         adherirEmpresaUseCase.adherir(CUIT, null);
      });

      assertEquals("Los datos no pueden estar vacíos.", exception.getMessage());
   }

}
