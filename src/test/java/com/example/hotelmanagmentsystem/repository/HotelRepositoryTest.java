package com.example.hotelmanagmentsystem.repository;

import com.example.hotelmanagmentsystem.entity.Hotel;
import com.example.hotelmanagmentsystem.enums.HotelStars;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class HotelRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager ;
    @Autowired
    private  HotelRepository hotelRepository ;
    private  Hotel hotel1 ;

    @BeforeEach
    public  void setUp()
    {
        hotel1 = new Hotel("hotel1" ,"cairo" , "01111111111", HotelStars.FIVE_STAR);

    }

    @Test
    public  void shouldSaveHotel_WhenValidDataProvided()
    {
        Hotel hotel = hotelRepository.save(hotel1);
        assertThat(hotel.getId()).isNotNull() ;
        System.out.println("successfully");
    }

    @Test
    void  shouldThrowAlreadyExistException_WhenHotelAlreadyExist()
    {
        Hotel hotel = new Hotel("hotel1" , "cairo" , "01111111111" , HotelStars.FIVE_STAR);
        hotelRepository.save(hotel1);
        Assertions.assertThrows(DataIntegrityViolationException.class,()->{hotelRepository.save(hotel);});
    }


    @Test
    void shouldReturnHotel_WhenHotelIsFoundByName ()
    {
       Hotel hotel2 = hotelRepository.save(hotel1) ;
        Hotel hotel= hotelRepository.findByName("hotel1");
        assertThat(hotel.equals(hotel2));
    }

    @Test
    void shouldReturnNull_WhenHotelIsNotFoundByName ()
    {
        Hotel hotel2 = hotelRepository.save(hotel1) ;
        Hotel hotel= hotelRepository.findByName("hotel12");
        assertThat(hotel).isNull();
    }


}
