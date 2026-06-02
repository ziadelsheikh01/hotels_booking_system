package com.example.hotelmanagmentsystem.controller;
import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.example.hotelmanagmentsystem.dto.hotel.HotelResponse;
import com.example.hotelmanagmentsystem.dto.hotel.HotelSearchRequest;
import com.example.hotelmanagmentsystem.enums.HotelStars;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.security.JwtFilter;
import com.example.hotelmanagmentsystem.security.JwtService;
import com.example.hotelmanagmentsystem.service.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
@AutoConfigureMockMvc(addFilters = false)
public class HotelControllerTest
{
    @Autowired
    MockMvc mockMvc ;
    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    HotelService hotelService ;
    private HotelResponse hotelResponse ;

    @Autowired
    private ObjectMapper objectMapper ;
    @BeforeEach
    public  void setUp()
    {

        hotelResponse = new HotelResponse(1L , "hilton"  , "cairo" , "01111111111", HotelStars.FIVE_STAR);
    }

    @Test
    public void ShouldReturn200_WhenDataIsValid() throws Exception
    {
        //arrange
        when(hotelService.create(any(AddHotelRequest.class))).thenReturn(hotelResponse) ;

        //act
        mockMvc.perform(
                post("/api/hotel")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name" : "hilton",
                                    "address" : "cairo",
                                    "phoneNumber" : "01111111111"
                                }
                                """)
               ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.address").value("cairo"))
                .andExpect(jsonPath("$.name").value("hilton"));
    }

    @Test
    public void ShouldReturn409_WhenHotelAlreadyExist() throws Exception
    {
        //arrange
        when(hotelService.create(any(AddHotelRequest.class))).thenThrow(new AlreadyExistException("Hotel is already saved before")) ;

        //act
        mockMvc.perform(
                        post("/api/hotel")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "name" : "hilton",
                                    "address" : "cairo",
                                    "phoneNumber" : "01111111111"
                                }
                                """)
                ).andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value("Hotel is already saved before"));
    }

    @Test
    public void searchHotel_WhenHotelIsFound_ShouldReturn200 () throws Exception
    {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest() ;
        hotelSearchRequest.setName("hilton");
        when(hotelService.search(any(HotelSearchRequest.class))).thenReturn(List.of(hotelResponse));

        mockMvc.perform(
                post("/api/hotel/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hotelSearchRequest)
                        )
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(hotelResponse.getName()))
                .andExpect(jsonPath("$[0].id").value(1L));

    }
}
