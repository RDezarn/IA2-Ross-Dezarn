package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

@RestController
public class RestApiController {


    @GetMapping("/poem")
    public Object getPoem() throws JsonProcessingException {

        try {

            //Using the PoetryDB API I will get all the poems that contains the word rain
            String url = "https://poetrydb.org/title/rain";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            List<Poem> poems = new ArrayList<>();

            for (JsonNode rt : root) {

                //Getting the name author and line count, which are simple strings
                String title = rt.get("title").asText();
                String author = rt.get("author").asText();
                int linecount = rt.get("linecount").asInt();

                //Iterating over the JSONArray to get the poem's lines
                ArrayList<String> lines = new ArrayList<String>();
                JsonNode holder = rt.get("lines");
                Iterator<JsonNode> line = holder.elements();
                while(line.hasNext()){
                    lines.add(line.next().asText());
                }
                Poem result = new Poem(title,author,lines,linecount);
                poems.add(result);

            }

            return poems;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /poem";

        }
    }

}
