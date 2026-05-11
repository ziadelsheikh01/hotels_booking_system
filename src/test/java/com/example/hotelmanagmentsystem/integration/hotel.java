package com.example.hotelmanagmentsystem.integration;

import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class hotel
{
    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    ObjectMapper objectMapper ;
    @Test
    public  void createHotel_WithExistData_shouldReturn409 () throws Exception
    {
        AddHotelRequest addHotelRequest = new AddHotelRequest("hilton" ,"cairo" , "01111111129") ;
        mockMvc.perform(
                post("/api/hotel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addHotelRequest)))
                .andExpect(status().isOk());

        mockMvc.perform(
                        post("/api/hotel")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(addHotelRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Hotel is already saved befoore"));

    }
    @Test
    public  void createHotel_WithValidData_shouldReturn200 () throws Exception
    {
        AddHotelRequest addHotelRequest = new AddHotelRequest("hilton" ,"cairo" , "01111111129") ;
        mockMvc.perform(
                        post("/api/hotel")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(addHotelRequest)))
                .andExpect(status().isOk());


    }

}
