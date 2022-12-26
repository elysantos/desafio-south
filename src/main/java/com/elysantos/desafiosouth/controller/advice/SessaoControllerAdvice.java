package com.elysantos.desafiosouth.controller.advice;

import com.elysantos.desafiosouth.controller.SessaoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice(assignableTypes = SessaoController.class)
public class SessaoControllerAdvice {

}
