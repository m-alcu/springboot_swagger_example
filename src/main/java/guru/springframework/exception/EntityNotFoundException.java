package guru.springframework.exception;

import guru.springframework.errorcodes.GlobalErrorCode;

/**
 * Entity not found exception.
 */
public class EntityNotFoundException extends AbstractCommonException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2825691505598480159L;

    private final String entityName;
    private final String entityId;

    /**
     * Instantiates a new entity not found exception.
     *
     * @param entityName the entity name
     * @param entityId the entity id
     */
    public EntityNotFoundException(String entityName, String entityId) {
        super(GlobalErrorCode.ENTITY_NOT_FOUND, entityName, entityId);
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
