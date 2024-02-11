package com.devsu.movimiento.rest;

import com.devsu.movimiento.domain.Cuenta;
import com.devsu.movimiento.domain.Movimiento;
import com.devsu.movimiento.dto.CreateMovimientoDTO;
import com.devsu.movimiento.enums.TipoMovimiento;
import com.devsu.movimiento.service.ApplyMovimientoService;
import com.devsu.movimiento.service.crud.MovimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApplyMovimientoService applyMovimientoService; // Simulamos el servicio que realiza la inserción

    @Test
    public void testCreateMovimiento() throws Exception {
        // Mockear el servicio para devolver una respuesta simulada
        Movimiento movimientoMock = new Movimiento();
        movimientoMock.setId(1L);
        movimientoMock.setCuenta(new Cuenta());
        movimientoMock.setValor(new BigDecimal("100.00"));
        when(applyMovimientoService.applyMovimiento(any(CreateMovimientoDTO.class))).thenReturn(movimientoMock);

        CreateMovimientoDTO createMovimientoDTO = new CreateMovimientoDTO();
        createMovimientoDTO.setNumeroCuenta(1L);
        createMovimientoDTO.setEstado(Boolean.TRUE);
        createMovimientoDTO.setTipoMovimiento(TipoMovimiento.AHORROS);
        createMovimientoDTO.setValor(new BigDecimal("100.00"));
        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createMovimientoDTO)))
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Movimiento movimiento = objectMapper.readValue(contentAsString, Movimiento.class);
                    assert movimiento.getId() != null;
                    assert movimiento.getValor().equals(createMovimientoDTO.getValor());
                });
    }
}
