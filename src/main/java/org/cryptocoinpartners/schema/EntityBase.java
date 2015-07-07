package org.cryptocoinpartners.schema;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * @author Tim Olson
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class EntityBase implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7893439827939854533L;

    @Id
    @Column(columnDefinition = "BINARY(16)", length = 16, updatable = true, nullable = false)
    public UUID getId() {
        ensureId();
        return id;
    }

    @Version
    public Integer getVersion() {
        if (version == null)
            return 0;
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Transient
    public Integer getRetryCount() {
        if (retryCount == null)
            return 0;
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public void incermentRetryCount() {
        retryCount = getRetryCount() + 1;
    }

    @Override
    public boolean equals(Object o) {
        // generated by IDEA
        if (this == o)
            return true;
        if (!(o instanceof EntityBase))
            return false;
        EntityBase that = (EntityBase) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        ensureId();
        return id.hashCode();
    }

    // JPA
    protected EntityBase() {
    }

    protected void setId(UUID id) {
        this.id = id;
    }

    private void ensureId() {
        if (id == null)
            id = UUID.randomUUID();
    }

    protected UUID id;
    protected Integer version;
    protected Integer retryCount;

}
