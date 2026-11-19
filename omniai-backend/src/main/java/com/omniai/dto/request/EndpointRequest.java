
package com.omniai.dto.request;

import com.omniai.entity.EndpointStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EndpointRequest(

    @NotBlank(message = "O hostname é obrigatório.")
    @Size(max = 150)
    String hostname,

    @Size(max = 150)
    String serialNumber,

    @Size(max = 100)
    String operatingSystem,

    @Size(max = 100)
    String osVersion,

    @Size(max = 45)
    String ipAddress,

    @Size(max = 17)
    String macAddress,

    EndpointStatus status

) {
}
