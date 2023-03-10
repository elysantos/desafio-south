package com.elysantos.desafiosouth.controller;

import com.elysantos.desafiosouth.model.domain.Pauta;
import com.elysantos.desafiosouth.model.presentation.PautaRequest;
import com.elysantos.desafiosouth.model.presentation.PautaResponse;
import com.elysantos.desafiosouth.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PautaControllerTest {
  private              MockMvc      mockMvc;
  private              ObjectMapper objectMapper;
  private              PautaService pautaService;
  private static final String       ENDPOINT     = "/pautas";
  private static final String       CONTENT_TYPE = "application/json";

  @BeforeEach
  void setUp() {
    pautaService = mock(PautaService.class);
    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders
        .standaloneSetup(new PautaController(pautaService))
        .build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void testCreateASuccessfulPauta() throws Exception {
    PautaRequest request = new PautaRequest();
    request.setTitulo("pauta titulo");

    when(pautaService.criar(any(Pauta.class))).thenReturn(new Pauta(request.getTitulo()));

    MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
        .contentType(CONTENT_TYPE)
        .content(objectMapper.writeValueAsString(request))
    ).andExpect(status().isCreated()).andReturn();

    PautaResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
        PautaResponse.class);

    assertNotNull(response.getId());
    assertEquals(response.getTitulo(), request.getTitulo());

    verify(pautaService, times(1)).criar(any());
  }

}