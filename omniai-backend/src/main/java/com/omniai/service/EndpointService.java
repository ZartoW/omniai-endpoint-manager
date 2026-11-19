
package com.omniai.service;

import com.omniai.entity.Endpoint;
import com.omniai.entity.EndpointStatus;
import com.omniai.repository.EndpointRepository;
import com.omniai.exception.EndpointConflictException;
import com.omniai.exception.EndpointNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EndpointService {

    private final EndpointRepository endpointRepository;

    public EndpointService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    // Cadastrar computador
    @Transactional
    public Endpoint create(Endpoint endpoint) {

        // Verificar hostname duplicado
        endpointRepository.findByHostname(endpoint.getHostname())
                .ifPresent(existing -> {
                    throw new EndpointConflictException(
                            "Já existe um computador com esse hostname."
                    );
                });

        // Verificar número de série duplicado
        if (endpoint.getSerialNumber() != null &&
                !endpoint.getSerialNumber().isBlank()) {

            endpointRepository.findBySerialNumber(endpoint.getSerialNumber())
                    .ifPresent(existing -> {
                        throw new EndpointConflictException(
                                "Já existe um computador com esse número de série."
                        );
                    });
        }

        return endpointRepository.save(endpoint);
    }

    // Listar todos os computadores
    @Transactional(readOnly = true)
    public List<Endpoint> findAll() {
        return endpointRepository.findAll();
    }

    // Buscar computador pelo ID
    @Transactional(readOnly = true)
    public Endpoint findById(UUID id) {
        return endpointRepository.findById(id)
                .orElseThrow(() -> new EndpointNotFoundException(
                        "Computador não encontrado."
                ));
    }

    // Buscar computador pelo hostname
    @Transactional(readOnly = true)
    public Endpoint findByHostname(String hostname) {
        return endpointRepository.findByHostname(hostname)
                .orElseThrow(() -> new EndpointNotFoundException(
                        "Computador não encontrado."
                ));
    }

    // Filtrar computadores pelo status
    @Transactional(readOnly = true)
    public List<Endpoint> findByStatus(EndpointStatus status) {
        return endpointRepository.findByStatus(status);
    }

    // Atualizar informações do computador
    @Transactional
    public Endpoint update(UUID id, Endpoint updatedEndpoint) {

        Endpoint endpoint = findById(id);

        // Verificar se outro computador utiliza o hostname
        endpointRepository.findByHostname(updatedEndpoint.getHostname())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new EndpointConflictException(
                            "Já existe um computador com esse hostname."
                    );
                });

        // Verificar se outro computador utiliza o número de série
        if (updatedEndpoint.getSerialNumber() != null &&
                !updatedEndpoint.getSerialNumber().isBlank()) {

            endpointRepository.findBySerialNumber(updatedEndpoint.getSerialNumber())
                    .filter(existing -> !existing.getId().equals(id))
                    .ifPresent(existing -> {
                        throw new EndpointConflictException(
                                "Já existe um computador com esse número de série."
                        );
                    });
        }

        // Atualizar os dados
        endpoint.setHostname(updatedEndpoint.getHostname());
        endpoint.setSerialNumber(updatedEndpoint.getSerialNumber());
        endpoint.setOperatingSystem(updatedEndpoint.getOperatingSystem());
        endpoint.setOsVersion(updatedEndpoint.getOsVersion());
        endpoint.setIpAddress(updatedEndpoint.getIpAddress());
        endpoint.setMacAddress(updatedEndpoint.getMacAddress());
        endpoint.setStatus(updatedEndpoint.getStatus());

        return endpointRepository.save(endpoint);
    }

    // Registrar comunicação do computador
    @Transactional
    public Endpoint updateLastSeen(UUID id) {

        Endpoint endpoint = findById(id);

        endpoint.setLastSeen(OffsetDateTime.now());
        endpoint.setStatus(EndpointStatus.ONLINE);

        return endpointRepository.save(endpoint);
    }

    // Excluir computador
    @Transactional
    public void delete(UUID id) {

        Endpoint endpoint = findById(id);

        endpointRepository.delete(endpoint);
    }
}
