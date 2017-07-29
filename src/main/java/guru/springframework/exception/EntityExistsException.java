package guru.springframework.exception;

import guru.springframework.errorcodes.GlobalErrorCode;

/**
 * Entity exists exception.
 */
public class EntityExistsException extends AbstractCommonException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7053106534975292101L;

    private final String entityName;
    private final String entityId;

    /**
     * Instantiates a new entity exists exception.
     *
     * @param entityName the entity name
     * @param entityId the entity id
     */
    public EntityExistsException(String entityName, String entityId) {
        super(GlobalErrorCode.ENTITY_EXISTS, entityName, entityId);
        this.entityName = entityName;
        this.entityId = entityId;
    }

    /**
     * Gets entity name.
     *
     * @return the entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Gets entity id.
     *
     * @return the entity id
     */
    public String getEntityId() {
        return entityId;
    }
}
