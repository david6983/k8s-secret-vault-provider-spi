# K8s secret vault provider Keycloak SPI

An open source Keycloak SPI to avoid mounting secrets in filesystem of the Keycloak pod in Kubernetes

## Compatibility

Keycloak 20.0.2 for the moment while I update it for Keycloak 23

## Installation

1. enable the spi using the env `SPI_K8S_SECRETS_ENABLED`
2. mount a volume to `/opt/keycloak/providers` to add the provider
3. Don't forget to setup the default service account to read secrets in the namespace if needed