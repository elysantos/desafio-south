package com.elysantos.desafiosouth.controller;

import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.model.presentation.SessaoRequest;
import com.elysantos.desafiosouth.model.presentation.SessaoResponse;
import com.elysantos.desafiosouth.service.SessaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
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

class SessaoControllerTest {
  private              MockMvc       mockMvc;
  private              ObjectMapper  objectMapper;
  private              SessaoService sessaoService;
  private static final String        ENDPOINT     = "/sessoes";
  private static final String        CONTENT_TYPE = "application/json";


  @BeforeEach
  void setUp() {
    sessaoService = mock(SessaoService.class);
    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders
        .standaloneSetup(new SessaoController(sessaoService))
        .build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void testCreateSuccessfulSessao() throws Exception {
    SessaoRequest request = new SessaoRequest();
    request.setInicio(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    request.setPautaID(UUID.randomUUID().toString());

    Sessao sessao = request.toDomain();

    when(sessaoService.createSession(any())).thenReturn(sessao);

    MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
        .contentType(CONTENT_TYPE)
        .content(objectMapper.writeValueAsString(request))
    ).andExpect(status().isCreated()).andReturn();

    SessaoResponse response = objectMapper
        .readValue(mvcResult.getResponse().getContentAsString(),
            SessaoResponse.class);

    assertNotNull(response);
    assertEquals(response.getPauta().getId(), request.getPautaID());

    verify(sessaoService, times(1)).createSession(any());

  }
}