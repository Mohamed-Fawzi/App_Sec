package tn.supcom.appsec.iam.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GrantPK implements Serializable {

    @Column(name = "tenant_id",nullable = false)
    private Short tenantId;
    @Column(name = "identity_id",nullable = false)
    private Long identityId;

    public GrantPK(){
    }

    public Short getTenantId() {
        return tenantId;
    }

    public void setTenantId(Short tenantId) {
        this.tenantId = tenantId;
    }

    public Long getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Long identityId) {
        this.identityId = identityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrantPK grantPK)) return false;
        return Objects.equals(tenantId, grantPK.tenantId) && Objects.equals(identityId, grantPK.identityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantId, identityId);
    }
}
