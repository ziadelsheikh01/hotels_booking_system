package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.example.hotelmanagmentsystem.dto.hotel.HotelResponse;
import com.example.hotelmanagmentsystem.entity.Hotel;
import com.example.hotelmanagmentsystem.enums.HotelStars;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.HotelMapper;
import com.example.hotelmanagmentsystem.repository.HotelRepository;
import com.example.hotelmanagmentsystem.service.serviceImp.HotelServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest
{
    @Mock
    private HotelRepository hotelRepository ;
    @Mock
    private HotelMapper hotelMapper;
    @InjectMocks
    private HotelServiceImp hotelService;

    Hotel hotel ;
    AddHotelRequest addHotelRequest ;

    HotelResponse hotelResponse ;
    @BeforeEach
    public  void setUp ()
    {
         hotel = new Hotel("hilton" ,"giza" , "0111111111" , HotelStars.FIVE_STAR) ;
        addHotelRequest = new AddHotelRequest("hilton" ,"giza" , "0111111111");
         hotelResponse = new HotelResponse(1L,"hilton" ,"giza" , "0111111111" , HotelStars.FIVE_STAR);

    }
    @Test
    public  void shouldCreateHotel_WhenValidDataProvided()
    {
       // arrange
       when(hotelMapper.toEntity(any())).thenReturn(hotel);
       when(hotelRepository.findByName(anyString())).thenReturn(null) ;
       when(hotelRepository.save(any())).thenReturn(hotel);
       when(hotelMapper.toDto(any(Hotel.class))).thenReturn(hotelResponse);

       //act
       HotelResponse hotelResponse1 = hotelService.create(addHotelRequest);

       //assert
        Assertions.assertThat(hotelResponse1.getId()).isNotNull();
    }

    @Test
    public  void shouldThrowAlreadyExistException_WhenHotelIsAlreadyExist()
    {
        // arrange
        when(hotelRepository.findByName(anyString())).thenReturn(hotel) ;

        //act and assert
        org.junit.jupiter.api.Assertions.assertThrows(AlreadyExistException.class, ()->hotelService.create(addHotelRequest));
    }

    @Test
    public  void shouldReturnHotel_WhenIdIsFound ()
    {
      //Arrange
        when(hotelRepository.findById(any())).thenReturn(Optional.of(hotel));
        when(hotelMapper.toDto(hotel)).thenReturn(hotelResponse);

        //act
       HotelResponse hotelResponse1 = hotelService.findById(1L) ;

        // assert
        Assertions.assertThat(hotelResponse1.getId()).isNotNull();
        Assertions.assertThat(hotelResponse1.getName()).isEqualTo(hotel.getName());
        Assertions.assertThat(hotelResponse1.getId()).isEqualTo(1L);
        Assertions.assertThat(hotelResponse1.getRating()).isEqualTo(HotelStars.FIVE_STAR);

    }

    @Test
    public  void shouldThrowsNotFoundException_WhenIdISNotFound()
    {
        //arrange
        when(hotelRepository.findById(any())).thenReturn(Optional.empty()) ;

        //act and assert
        org.junit.jupiter.api.Assertions.assertThrows(NotFoundException.class,()->hotelService.findById(1L)) ;
    }

}
