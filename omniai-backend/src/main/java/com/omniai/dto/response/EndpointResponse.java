
package com.omniai.dto.response;

import com.omniai.entity.Endpoint;
import com.omniai.entity.EndpointStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EndpointResponse(

    UUID id,
    String hostname,
    String serialNumber,
    String operatingSystem,
    String osVersion,
    String ipAddress,
    String macAddress,
    EndpointStatus status,
    OffsetDateTime lastSeen,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt

) {

    public static EndpointResponse fromEntity(Endpoint endpoint) {

        return new EndpointResponse(
            endpoint.getId(),
            endpoint.getHostname(),
            endpoint.getSerialNumber(),
            endpoint.getOperatingSystem(),
            endpoint.getOsVersion(),
            endpoint.getIpAddress(),
            endpoint.getMacAddress(),
            endpoint.getStatus(),
            endpoint.getLastSeen(),
            endpoint.getCreatedAt(),
            endpoint.getUpdatedAt()
        );
    }
}
