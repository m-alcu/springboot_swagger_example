package guru.springframework.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Abstraction for audit purposes. Must be extended by auditable entities.
 */
@Data
public abstract class AuditableEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6337883453805250851L;

    /** The creation date. */
    protected transient LocalDateTime creationDate;

    /** The created by. */
    protected String createdBy;

    /** The modification date. */
    protected transient LocalDateTime modificationDate;

    /** The modified by. */
    protected String modifiedBy;

    /**
     * Gets the creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate the new creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the modification date.
     *
     * @return the modification date
     */
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets the modification date.
     *
     * @param modificationDate the new modification date
     */
    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the new modified by
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
