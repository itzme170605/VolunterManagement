package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public class ScheduleItemDeserializer extends JsonDeserializer<ScheduleItem> {
    @Override
    public ScheduleItem deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        var node = jp.readValueAsTree();

        Need need = mapper.treeToValue(node.get("need"), Need.class);
        Date dateTime = mapper.treeToValue(node.get("dateTime"), Date.class);

        return new ScheduleItem(need, dateTime);
    }
}