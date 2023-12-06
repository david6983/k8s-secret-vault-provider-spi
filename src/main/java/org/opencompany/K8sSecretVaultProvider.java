package org.opencompany;

import org.jboss.logging.Logger;
import org.keycloak.vault.AbstractVaultProvider;
import org.keycloak.vault.VaultKeyResolver;
import org.keycloak.vault.VaultRawSecret;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class K8sSecretVaultProvider extends AbstractVaultProvider {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

    public K8sSecretVaultProvider(String realm, List<VaultKeyResolver> configuredResolvers) {
        super(realm, configuredResolvers);
        logger.info("[K8sSecretVaultProvider] this vault is cool");
    }

    @Override
    protected VaultRawSecret obtainSecretInternal(String s) {
        return null;
    }

    @Override
    public void close() {

    }
}
