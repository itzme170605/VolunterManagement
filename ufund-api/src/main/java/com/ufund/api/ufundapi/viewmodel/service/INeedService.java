package com.ufund.api.ufundapi.viewmodel.service;

import com.ufund.api.ufundapi.model.Need;
import java.io.IOException;

public interface INeedService {
    Need getNeed(int id) throws IOException;
    Need[] getAllNeeds() throws IOException;
    Need[] searchNeeds(String name) throws IOException;
    Need createNeed(Need need) throws IOException;
    Need updateNeed(Need need) throws IOException;
    boolean deleteNeed(int id) throws IOException;
}