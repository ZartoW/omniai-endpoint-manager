
CREATE TABLE endpoints (
    id UUID PRIMARY KEY,

    hostname VARCHAR(150) NOT NULL,
    serial_number VARCHAR(150),

    operating_system VARCHAR(100),
    os_version VARCHAR(100),

    ip_address VARCHAR(45),
    mac_address VARCHAR(17),

    status VARCHAR(20) NOT NULL DEFAULT 'UNKNOWN',

    last_seen TIMESTAMP WITH TIME ZONE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_endpoint_status
        CHECK (status IN ('ONLINE', 'OFFLINE', 'UNKNOWN'))
);

CREATE UNIQUE INDEX idx_endpoints_serial_number
    ON endpoints(serial_number)
    WHERE serial_number IS NOT NULL;

CREATE INDEX idx_endpoints_hostname
    ON endpoints(hostname);

CREATE INDEX idx_endpoints_status
    ON endpoints(status);
