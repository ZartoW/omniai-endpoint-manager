
package com.omniai.controller;

import com.omniai.dto.request.EndpointRequest;
import com.omniai.dto.response.EndpointResponse;
import com.omniai.entity.Endpoint;
import com.omniai.entity.EndpointStatus;
import com.omniai.service.EndpointService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/endpoints")
public class EndpointController {

    private final EndpointService endpointService;

    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    // Cadastrar computador
    @PostMapping
    public ResponseEntity<EndpointResponse> create(
            @Valid @RequestBody EndpointRequest request) {

        Endpoint endpoint = new Endpoint(
                request.hostname(),
                request.serialNumber(),
                request.operatingSystem(),
                request.osVersion(),
                request.ipAddress(),
                request.macAddress()
        );

        if (request.status() != null) {
            endpoint.setStatus(request.status());
        }

        Endpoint savedEndpoint = endpointService.create(endpoint);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(EndpointResponse.fromEntity(savedEndpoint));
    }

    // Listar computadores
    @GetMapping
    public ResponseEntity<List<EndpointResponse>> findAll(
            @RequestParam(required = false) EndpointStatus status) {

        List<Endpoint> endpoints = status == null
                ? endpointService.findAll()
                : endpointService.findByStatus(status);

        List<EndpointResponse> response = endpoints.stream()
                .map(EndpointResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }

    // Buscar computador pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<EndpointResponse> findById(
            @PathVariable UUID id) {

        Endpoint endpoint = endpointService.findById(id);

        return ResponseEntity.ok(
                EndpointResponse.fromEntity(endpoint)
        );
    }

    // Atualizar computador
    @PutMapping("/{id}")
    public ResponseEntity<EndpointResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody EndpointRequest request) {

        Endpoint updatedEndpoint = new Endpoint(
                request.hostname(),
                request.serialNumber(),
                request.operatingSystem(),
                request.osVersion(),
                request.ipAddress(),
                request.macAddress()
        );

        updatedEndpoint.setStatus(
                request.status() != null
                        ? request.status()
                        : EndpointStatus.UNKNOWN
        );

        Endpoint endpoint = endpointService.update(id, updatedEndpoint);

        return ResponseEntity.ok(
                EndpointResponse.fromEntity(endpoint)
        );
    }

    // Registrar comunicação do computador
    @PatchMapping("/{id}/heartbeat")
    public ResponseEntity<EndpointResponse> heartbeat(
            @PathVariable UUID id) {

        Endpoint endpoint = endpointService.updateLastSeen(id);

        return ResponseEntity.ok(
                EndpointResponse.fromEntity(endpoint)
        );
    }

    // Excluir computador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id) {

        endpointService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
