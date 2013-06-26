package org.motechproject.commons.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class TenantIdentity {
    private IdentityProvider identityProvider;

    public TenantIdentity() {
        this(new SystemIdentityProvider());
    }

    public TenantIdentity(IdentityProvider identityProvider) {
        setIdentityProvider(identityProvider);
    }

    private void setIdentityProvider(IdentityProvider identityProvider) {
        this.identityProvider = new PropertyIdentityProvider(identityProvider);
    }

    public String getId() {
        String identity = identityProvider.getIdentity();
        if (StringUtils.isNotBlank(identity)) {
            return identity.toLowerCase();
        }
        return identity;
    }
}



class PropertyIdentityProvider implements IdentityProvider {
    private IdentityProvider originalIdentityProvider;

    private String tenantIdFromProperty;

    public PropertyIdentityProvider(IdentityProvider originalIdentityProvider) {
        this.originalIdentityProvider = originalIdentityProvider;
        Resource resource = new ClassPathResource("/tenant.properties");
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tenantIdFromProperty = props.getProperty("tenant.id");
    }

    @Override
    public String getIdentity() {
        return StringUtils.isBlank(tenantIdFromProperty) ? originalIdentityProvider.getIdentity() : tenantIdFromProperty;
    }
}
