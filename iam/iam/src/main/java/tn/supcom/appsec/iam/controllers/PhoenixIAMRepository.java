package tn.supcom.appsec.iam.controllers;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import tn.supcom.appsec.iam.entities.Grant;
import tn.supcom.appsec.iam.entities.Identity;
import tn.supcom.appsec.iam.entities.Tenant;

import java.util.HashSet;
import java.util.Optional;

@Singleton
public class PhoenixIAMRepository {
    @Inject
    private EntityManager entityManager;

    public Tenant findTenantByName(String name){
        return entityManager.createQuery("select t from Tenant t where name =:name",Tenant.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    public Identity findIdentityByUsername(String username){
        return entityManager.createQuery("select i from Identity i where username=:username",Identity.class)
                .setParameter("username",username)
                .getSingleResult();
    }

    public Optional<Grant> findGrant(String tenantName,Long identityId){
        Tenant tenant = findTenantByName(tenantName);
        if(tenant==null){
            throw new IllegalArgumentException("Invalid Client Id!");
        }
        return Optional.of(entityManager.createQuery("select g from Grant g where g.id.tenantId =:tenantId and g.id.identityId = :identityId",Grant.class)
                .setParameter("tenantId",tenant.getId())
                .setParameter("identityId",identityId)
                .getSingleResult());
    }
    public String[] getRoles(String username){
        var query = entityManager.createQuery("select i.roles from Identity i where username=:username",Long.class);
        query.setParameter("username",username);
        var roles = query.getSingleResult();
        var ret = new HashSet<String>();
        for(Role role:Role.values()){
            if((roles&role.getValue())!=0L){
                String value = Role.byValue(role.getValue());
                if (value==null){
                    continue;
                }
                ret.add(value);
            }
        }
        return ret.toArray(new String[0]);
    }
}
