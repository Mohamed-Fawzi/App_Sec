package tn.supcom.appsec.iam.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "issued_grants")
public class Grant implements RootEntity<GrantPK> {
    @EmbeddedId
    private GrantPK id;
    @Version
    private long version;
    @MapsId("tenantId")
    @ManyToOne
    private Tenant tenant;
    @MapsId("identityId")
    @ManyToOne
    private Identity identity;

    @Column(name = "approved_scopes")
    private String approvedScopes;

    @Column(name = "issuance_date_time")
    private LocalDateTime issuanceDateTime;

    @Override
    public GrantPK getId() {
        return id;
    }

    @Override
    public void setId(GrantPK id) {
        this.id = id;
    }

    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public void setVersion(long version) {
        this.version = version;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String getApprovedScopes() {
        return approvedScopes;
    }

    public void setApprovedScopes(String approvedScopes) {
        this.approvedScopes = approvedScopes;
    }

    public LocalDateTime getIssuanceDateTime() {
        return issuanceDateTime;
    }

    public void setIssuanceDateTime(LocalDateTime issuanceDateTime) {
        this.issuanceDateTime = issuanceDateTime;
    }
}
