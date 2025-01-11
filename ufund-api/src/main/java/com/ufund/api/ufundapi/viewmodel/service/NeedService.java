package com.ufund.api.ufundapi.viewmodel.service;

import org.springframework.stereotype.Service;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.persistence.INeedDAO;
import com.ufund.api.ufundapi.viewmodel.controller.NeedController;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class NeedService implements INeedService {
    private static final Logger LOG = Logger.getLogger(NeedController.class.getName());
    private final INeedDAO needDAO;

    public NeedService(INeedDAO needDAO) {
        this.needDAO = needDAO;
    }

    @Override
    public Need getNeed(int id) throws IOException {
        return needDAO.getNeed(id);
    }

    @Override
    public Need[] getAllNeeds() throws IOException {
        return needDAO.getNeeds();
    }

    @Override
    public Need[] searchNeeds(String name) throws IOException {
        return needDAO.findNeeds(name);
    }

    @Override
    public Need createNeed(Need need) throws IOException {
        // Check if the provided ID already exists
        if (needDAO.getNeed(need.getId()) != null) {
            // Generate a unique ID since the provided one is duplicate
            needDAO.createNeed(need);
            LOG.info("Duplicate ID detected. Assigned new unique ID");
        }
        return needDAO.createNeed(need);
    }
    @Override
    public Need updateNeed(Need need) throws IOException {
        if (needDAO.getNeed(need.getId()) == null) {
            throw new IllegalArgumentException("Need not found.");
        }
        return needDAO.updateNeed(need);
    }

    @Override
    public boolean deleteNeed(int id) throws IOException {
        return needDAO.deleteNeed(id);
    }
}