package guru.springframework.dao;

import java.util.List;

/**
 * CRUD basic operations for entities.
 *
 * @param <K> Primary Key
 * @param <V> Entity class
 */
public interface CrudDao<K, V> {

    /**
     * Find all the rows of a Entity.
     *
     * @return all the entities of database
     */
    List<V> findAll();

    /**
     * Find the entity by its id.
     *
     * @param id Primary key
     * @return the entity found
     */
    V findById(K id);

    /**
     * Insert the entity in the database.
     *
     * @param entity which will be inserted
     * @return rows affected
     */
    int insert(V entity);

    /**
     * Update the entity in the database.
     *
     * @param entity which will be updated
     * @return rows affected
     */
    int update(V entity);

    /**
     * Delete the entity with the primary key equals to ID.
     *
     * @param id Primary key
     * @return rows affected
     */
    int delete(K id);

    /**
     * Check if a entity exists by it id.
     *
     * @param id Primary key
     * @return true if exists or false if not
     */
    boolean exists(K id);

}
