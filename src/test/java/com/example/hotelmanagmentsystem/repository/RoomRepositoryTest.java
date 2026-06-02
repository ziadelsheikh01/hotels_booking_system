package com.example.hotelmanagmentsystem.repository;
import com.example.hotelmanagmentsystem.entity.Hotel;
import com.example.hotelmanagmentsystem.entity.Room;
import com.example.hotelmanagmentsystem.enums.HotelStars;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.enums.RoomType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.math.BigDecimal;
@DataJpaTest
public class RoomRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager ;
    @Autowired
    private RoomRepository roomRepository ;
    private Hotel hotel1 ;
    private Room room1 ;

    @BeforeEach
    public void setUp()
    {
        hotel1 = new Hotel("hotel1" ,"cairo" , "01111111111", HotelStars.FIVE_STAR);
        room1 = new Room(1,new BigDecimal(1500), RoomStatus.AVAILABLE, RoomType.DOUBLE,hotel1);
        testEntityManager.persist(hotel1);
        testEntityManager.persist(room1);


    }

    @Test
    public void shouldReturnTrue_whenRoomFoundINHotel()
    {
        Assertions.assertTrue(roomRepository.findRoomInHotel(hotel1.getId(), 1));


    }
    @Test
    public void shouldReturnFalse_whenRoomNotFoundINHotel()
    {
        Assertions.assertFalse(roomRepository.findRoomInHotel(hotel1.getId(),2));


    }
}
