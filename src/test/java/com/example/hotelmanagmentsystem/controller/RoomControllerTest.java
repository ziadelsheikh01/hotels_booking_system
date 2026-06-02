package com.example.hotelmanagmentsystem.controller;

import com.example.hotelmanagmentsystem.dto.room.RoomRequest;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.enums.RoomType;
import com.example.hotelmanagmentsystem.security.JwtFilter;
import com.example.hotelmanagmentsystem.security.JwtService;
import com.example.hotelmanagmentsystem.service.serviceImp.HotelServiceImp;
import com.example.hotelmanagmentsystem.service.serviceImp.RoomServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

@WebMvcTest(controllers = RoomController.class)
@AutoConfigureMockMvc(addFilters = false)

public class RoomControllerTest
{
    @Autowired
    private MockMvc mockMvc ;
    @MockBean
    private RoomServiceImp roomServiceImp ;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;
    private RoomRequest roomRequest ;
    @BeforeEach
    public  void  setUp()
    {
        roomRequest = new RoomRequest(1, BigDecimal.valueOf(2000), RoomStatus.AVAILABLE, RoomType.DOUBLE);
    }

    @Test
    public void shouldReturn200_WhenAddValidRoom() throws Exception
    {
        doNothing().when(roomServiceImp).AddRoom(anyLong(),any(RoomRequest.class));
        mockMvc.perform(
                post("/api/hotel/1/room")
                        .contentType("application/json")
                        .content(
                                """
                                        {
                                          "number": 101,
                                          "price": 1500.00,
                                          "roomStatus": "AVAILABLE",
                                          "roomType": "DOUBLE"
                                        }
                                        """
                        )
        )
                .andExpect(status().isCreated());

    }
}
