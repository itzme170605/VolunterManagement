package com.ufund.api.ufundapi.model.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;

/**
 * This class implments the INeedDAO interface using a file
 */

@Component
public class NeedFileDAO implements INeedDAO {
    private Map<Integer, Need> needs;
    private ObjectMapper objectMapper;
    private String filename;
    private static int nextId;

    public NeedFileDAO(@Value("${needs.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        this.needs = new HashMap<>();
        loadNeeds();
    }

    /**
     * Generates the next id for a new {@linkplain Need need}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    @Override
    public boolean deleteNeed(int id) throws IOException {
        synchronized (needs) {
            if (needs.containsKey(id)) {
                needs.remove(id);
                saveNeeds(); // Persist changes to the file
                return true; // Deletion successful
            } else {
                return false; // Need with given ID not found
            }
        }
    }

    @Override
    public Need[] findNeeds(String name) throws IOException {
        synchronized (needs) {
            String lowerCaseName = name.toLowerCase();
            return needs.values().stream()
                .filter(need -> need.getName().toLowerCase().contains(lowerCaseName))
                .toArray(Need[]::new);
        }
    }

    @Override
    public Need[] getNeeds() throws IOException {
        synchronized(needs){
            ArrayList<Need> needArrayList = new ArrayList<>();
            for(Need need: needs.values()) {
                needArrayList.add(need);
            }
            Need[] needArray = new Need[needArrayList.size()];
            needArrayList.toArray(needArray);
            return needArray;
        }
    }


    @Override
    public Need getNeed(int id) throws IOException {
        synchronized(needs) {  
            if(this.needs.containsKey(id)) {
                return needs.get(id);
            } else {
                return null;
            }
        }
    }


    @Override
    public Need createNeed(Need need) throws IOException {
        synchronized (needs) {
            /* Create a new need since id needs to be unique
            */
            Need newNeed = new Need(nextId(), need.getName(), 
                need.getFacility(), need.getDescription(), 
                need.getDatetime(), need.getRequired(),true);
            needs.put(newNeed.getId(), newNeed);
            saveNeeds(); // May throw an IOException
            return newNeed;
        }
    }


    @Override
    public Need updateNeed(Need need) throws IOException {
        synchronized(needs) {
            if(!needs.containsKey(need.getId())) {
                return null;        //need doenst exists
            } else {
                needs.put(need.getId(), need); //update the need
                return need;
            }
        }
    }

    private boolean loadNeeds() throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            Need[] needsArray = objectMapper.readValue(file, Need[].class);
            for (Need need : needsArray) {
                needs.put(need.getId(), need);
                // Update nextId to ensure unique IDs
                if (need.getId() >= nextId) {
                    nextId = need.getId() + 1;
                }
            }
        } else {
            // If the file doesn't exist, initialize it
            saveNeeds();
            return true;
        }
        return false;
    }
    
    private boolean saveNeeds() throws IOException {
        Need[] needsArray = needs.values().toArray(new Need[0]);
        objectMapper.writeValue(new File(filename), needsArray);
        return true;
    }

}
