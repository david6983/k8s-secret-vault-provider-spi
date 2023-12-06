package org.opencompany;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.vault.AbstractVaultProviderFactory;
import org.keycloak.vault.VaultProvider;

import java.lang.invoke.MethodHandles;

public class K8sSecretVaultProviderFactory extends AbstractVaultProviderFactory {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

    public static final String PROVIDER_ID = "k8s-secrets";

    @Override
    public VaultProvider create(KeycloakSession keycloakSession) {
        return new K8sSecretVaultProvider(getRealmName(keycloakSession), super.keyResolvers);
    }

    @Override
    public void init(Config.Scope config) {
        super.init(config);

        try (KubernetesClient k8s = new KubernetesClientBuilder().build()) {
            // Print names of all pods in specified namespace
            k8s.pods().inNamespace("default").list()
                    .getItems()
                    .stream()
                    .map(Pod::getMetadata)
                    .map(ObjectMeta::getName)
                    .forEach(logger::info);
        }

        logger.info("[K8sSecretVaultProviderFactory] vault configured from kubernetes secrets");
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
