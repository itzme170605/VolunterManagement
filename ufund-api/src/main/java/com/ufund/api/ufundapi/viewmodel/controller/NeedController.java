package com.ufund.api.ufundapi.viewmodel.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.viewmodel.service.INeedService;

@RestController
@RequestMapping("/api/needs")
public class NeedController {
    private static final Logger LOG = Logger.getLogger(NeedController.class.getName());
    private INeedService needService;

    public NeedController(INeedService needService) {
        this.needService = needService;
    }

    /**
     * Responds to the GET request for a {@linkplain Need need} for the given id
     *
     * @param id The id used to locate the {@link Need need}
     *
     * @return ResponseEntity with {@link Need need} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */

    @GetMapping("/{id}")
    public ResponseEntity<Need> getNeed(@PathVariable int id) {
        LOG.info("GET /needs/" + id);
        try {
            Need need = needService.getNeed(id);
            if (need != null)
                return new ResponseEntity<Need>(need,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all needs
     * 
     * @return The needs response
     */
    @GetMapping("")
    public ResponseEntity<?> getNeeds() {
        LOG.info("GET /api/needs");
        try {
            Need[] allNeeds = needService.getAllNeeds();
            // Always return 200 OK, even if the list is empty.
            return new ResponseEntity<Need[]>(allNeeds, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error getting needs: " + e.getMessage());
            return new ResponseEntity<String>("Error getting needs: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

    /**
     * Searches for needs
     * @param name Of the need
     * @return Need array
     */
    @GetMapping("/")
    public ResponseEntity<Need[]> searchNeeds(@RequestParam String name) {
        LOG.info("GET /api/needs/?name=" + name);
        try {
            Need[] needs = needService.searchNeeds(name);
            if (needs != null && needs.length > 0) {
                return new ResponseEntity<Need[]>(needs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Delete a Need
     * @param id Of Need being deleted
     * @return The delete Need response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Need> deleteNeed(@PathVariable int id) {
        LOG.info("DELETE /api/needs/" + id);
        try { 
            boolean deleted = needService.deleteNeed(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else { 
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            // Return HTTP 500 in case of exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain Need need} with the provided {@linkplain Need need} object, if it exists
     * 
     * @param need The {@link Need need} to update
     * 
     * @return ResponseEntity with updated {@link Need need} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Need> updateNeed(@RequestBody Need need) {
        LOG.info("PUT /needs " + need);

        try {
            ResponseEntity<Need> response = this.getNeed(need.getId());
            // Check if the status is NOT_FOUND
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } 
            // Check if the status is OK
            else if (response.getStatusCode() == HttpStatus.OK) {
                Need updatedNeed = this.needService.updateNeed(need);
                return new ResponseEntity<>(updatedNeed, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("")
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /needs " + need);
        try {
            ResponseEntity<Need> status = getNeed(need.getId());
            if (status.getStatusCode() == HttpStatus.NOT_FOUND) {
                Need output = needService.createNeed(need);
                return new ResponseEntity<>(output, HttpStatus.CREATED);
            } else if (status.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error creating Need: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
