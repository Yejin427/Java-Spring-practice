package com.example.practice2.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    @Test
    public void java_to_json() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("순쇠고기 패티", "양상추", "치즈", "참깨빵");
        Burger burger = new Burger("빅맥", 5500, ingredients);

        String json = objectMapper.writeValueAsString(burger);
        String expected = "{\"name\":\"빅맥\",\"price\":5500,\"ingredients\":[\"순쇠고기 패티\",\"양상추\",\"치즈\",\"참깨빵\"]}";
        assertEquals(expected, json);

        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void json_to_java() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //json 만들기
//        {
//            "name": "빅맥",
//            "price": 5500
//            "ingrediets": ["순쇠고기 패티", "양상추", "치즈", "참깨빵"]
//        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "빅맥");
        objectNode.put("price", 5500);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("순쇠고기 패티");
        arrayNode.add("양상추");
        arrayNode.add("치즈");
        arrayNode.add("참깨빵");
        objectNode.set("ingredients", arrayNode);
        String json = objectNode.toString();

        Burger burger = objectMapper.readValue(json, Burger.class);
        List<String> ingredients = Arrays.asList("순쇠고기 패티", "양상추", "치즈", "참깨빵");
        Burger expected = new Burger("빅맥", 5500, ingredients);

        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }
}