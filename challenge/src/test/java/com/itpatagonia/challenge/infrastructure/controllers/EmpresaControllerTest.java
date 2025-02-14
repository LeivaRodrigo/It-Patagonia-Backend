package com.itpatagonia.challenge.infrastructure.controllers;

import com.itpatagonia.challenge.application.usecases.AdherirEmpresaUseCase;
import com.itpatagonia.challenge.application.usecases.ObtenerEmpresasAdheridasUltimoMesUseCase;
import com.itpatagonia.challenge.application.usecases.ObtenerEmpresasConTransferenciasUltimoMesUseCase;
import com.itpatagonia.challenge.domain.model.Empresa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmpresaControllerTest {

   private final static String CUIT = "30-12345678-9";
   private final static String RAZON_SOCIAL = "Empresa Test";

   @Mock
   private AdherirEmpresaUseCase adherirEmpresaUseCase;

   @Mock
   private ObtenerEmpresasAdheridasUltimoMesUseCase obtenerEmpresasAdheridasUltimoMesUseCase;

   @Mock
   private ObtenerEmpresasConTransferenciasUltimoMesUseCase obtenerEmpresasConTransferenciasUltimoMesUseCase;

   @InjectMocks
   private EmpresaController empresaController;

   @Test
   @DisplayName("Adhiere empresa exitosamente")
   void adherirEmpresaTest() {
      Empresa empresaMock = new Empresa(CUIT, RAZON_SOCIAL, LocalDate.now());
      when(adherirEmpresaUseCase.adherir(CUIT, RAZON_SOCIAL)).thenReturn(empresaMock);

      ResponseEntity<Empresa> response = empresaController.adherirEmpresa(CUIT, RAZON_SOCIAL);

      assertNotNull(response.getBody());
      assertEquals(CUIT, response.getBody().getCuit());
      assertEquals(RAZON_SOCIAL, response.getBody().getRazonSocial());
   }

   @Test
   @DisplayName("Obtiene las empresas del ultimo mes exitosamente")
   void obtenerEmpresasAdheridasUltimoMesTest() {
      List<Empresa> empresasMock = List.of(new Empresa(CUIT, RAZON_SOCIAL, LocalDate.now()));
      when(obtenerEmpresasAdheridasUltimoMesUseCase.findByFechaAdhesionAfter()).thenReturn(empresasMock);

      List<Empresa> empresas = empresaController.obtenerEmpresasAdheridasUltimoMes();

      assertNotNull(empresas);
      assertFalse(empresas.isEmpty());
   }

   @Test
   @DisplayName("Obtiene las empresas con transferencia del ultimo mes exitosamente")
   void obtenerEmpresasConTransferenciasUltimoMesTest() {
      List<Empresa> empresasMock = List.of(new Empresa(CUIT, RAZON_SOCIAL, LocalDate.now()));
      when(obtenerEmpresasConTransferenciasUltimoMesUseCase.findEmpresasConTransferenciasUltimoMes()).thenReturn(empresasMock);

      List<Empresa> empresas = empresaController.obtenerEmpresasConTransferenciasUltimoMes();

      assertNotNull(empresas);
      assertFalse(empresas.isEmpty());
   }

}
