
package com.omniai.repository;

import com.omniai.entity.Endpoint;
import com.omniai.entity.EndpointStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {

    Optional<Endpoint> findByHostname(String hostname);

    Optional<Endpoint> findBySerialNumber(String serialNumber);

    List<Endpoint> findByStatus(EndpointStatus status);
}
