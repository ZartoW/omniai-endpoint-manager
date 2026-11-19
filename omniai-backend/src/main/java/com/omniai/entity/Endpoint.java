
package com.omniai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "endpoints",
    indexes = {
        @Index(name = "idx_endpoints_hostname", columnList = "hostname"),
        @Index(name = "idx_endpoints_status", columnList = "status")
    }
)
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String hostname;

    @Column(name = "serial_number", length = 150)
    private String serialNumber;

    @Column(name = "operating_system", length = 100)
    private String operatingSystem;

    @Column(name = "os_version", length = 100)
    private String osVersion;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "mac_address", length = 17)
    private String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EndpointStatus status = EndpointStatus.UNKNOWN;

    @Column(name = "last_seen")
    private OffsetDateTime lastSeen;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    // Construtor vazio exigido pelo JPA
    public Endpoint() {
    }

    // Construtor para cadastrar um computador
    public Endpoint(
            String hostname,
            String serialNumber,
            String operatingSystem,
            String osVersion,
            String ipAddress,
            String macAddress
    ) {
        this.hostname = hostname;
        this.serialNumber = serialNumber;
        this.operatingSystem = operatingSystem;
        this.osVersion = osVersion;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.status = EndpointStatus.UNKNOWN;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
        this.updatedAt = OffsetDateTime.now();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        this.updatedAt = OffsetDateTime.now();
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        this.updatedAt = OffsetDateTime.now();
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
        this.updatedAt = OffsetDateTime.now();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        this.updatedAt = OffsetDateTime.now();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        this.updatedAt = OffsetDateTime.now();
    }

    public EndpointStatus getStatus() {
        return status;
    }

    public void setStatus(EndpointStatus status) {
        this.status = status;
        this.updatedAt = OffsetDateTime.now();
    }

    public OffsetDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(OffsetDateTime lastSeen) {
        this.lastSeen = lastSeen;
        this.updatedAt = OffsetDateTime.now();
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
