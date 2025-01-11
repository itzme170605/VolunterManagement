
package com.ufund.api.ufundapi.model.persistence;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Need;


/**
 * Interface for Need Data Access Object
 */

public interface INeedDAO {

    /**
     * Retrieves all Needs.
     *
     * @return An array of all Needs.
     * @throws IOException If an error occurs during retrieval.
     */
    Need[] getNeeds() throws IOException;

    /**
     * Retrieves a Need by its ID.
     *
     * @param id The ID of the Need.
     * @return The Need with the given ID, or null if not found.
     * @throws IOException If an error occurs during retrieval.
     */
    Need getNeed(int id) throws IOException;

    /**
     * Creates a new Need.
     *
     * @param need The Need to create.
     * @return The created Need, or null if a Need with the same name exists.
     * @throws IOException If an error occurs during creation.
     */
    Need createNeed(Need need) throws IOException;

    /**
     * Updates an existing Need.
     *
     * @param need The Need to update.
     * @return The updated Need, or null if the Need does not exist.
     * @throws IOException If an error occurs during the update.
     */
    Need updateNeed(Need need) throws IOException;

    /**
     * Deletes a Need by its ID.
     *
     * @param id The ID of the Need to delete.
     * @return True if the Need was deleted, false if not found.
     * @throws IOException If an error occurs during deletion.
     */
    boolean deleteNeed(int id) throws IOException;

    /**
     * Finds Needs by partial name match.
     *
     * @param name The partial name to search for.
     * @return An array of Needs that match the search criteria.
     * @throws IOException If an error occurs during the search.
     */
    Need[] findNeeds(String name) throws IOException;
}

