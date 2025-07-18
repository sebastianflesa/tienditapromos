package com.tiendita.tienditapromos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.tiendita.tienditapromos.service.MensajeService;

@RestController
@RequestMapping("/api")
public class MensajeController {

	private final MensajeService mensajeService;

	public MensajeController(MensajeService mensajeService) {

		this.mensajeService = mensajeService;
	}

	@GetMapping("")
	public ResponseEntity<String> index() {
  		return ResponseEntity.ok("Microservicio de promos API, envia mensajes a broker RabbitMQ");
	}

	@PostMapping("/promos/crear")
	public ResponseEntity<Object> enviar(@RequestBody String mensaje) {
		mensajeService.enviarMensaje(mensaje);
		System.err.println("Mensaje enviado al broker: " + mensaje);
		return ResponseEntity.ok(
			java.util.Collections.singletonMap("mensaje", "Mensaje enviado al broker: " + mensaje)
		);
	}

	/*
	@PostMapping("/usuarios")
	public ResponseEntity<String> enviarObjetoUsuario(@RequestBody UsuarioDTO usuario) {

		mensajeService.enviarObjeto(usuario);
		return ResponseEntity.ok("Mensaje enviado: " + usuario.toString());
	}

	@PostMapping("/prodductos")
	public ResponseEntity<String> enviarObjetoProducto(@RequestBody ProductoDTO producto) {

		mensajeService.enviarObjeto(producto);
		return ResponseEntity.ok("Mensaje enviado: " + producto.toString());
	}*/
}
