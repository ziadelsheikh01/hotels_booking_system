package com.example.hotelmanagmentsystem.service.serviceImp;

import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.example.hotelmanagmentsystem.dto.hotel.HotelResponse;
import com.example.hotelmanagmentsystem.dto.hotel.HotelSearchRequest;
import com.example.hotelmanagmentsystem.dto.hotel.UpdateHotelRequest;
import com.example.hotelmanagmentsystem.entity.Hotel;
import com.example.hotelmanagmentsystem.enums.HotelStars;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.HotelMapper;
import com.example.hotelmanagmentsystem.repository.HotelRepository;
import com.example.hotelmanagmentsystem.service.HotelService;
import com.example.hotelmanagmentsystem.specification.HotelSpecification;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class HotelServiceImp implements HotelService
{
    private HotelRepository hotelRepository ;
    private HotelMapper hotelMapper ;

    public HotelServiceImp(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public List<HotelResponse> search(HotelSearchRequest hotelSearchRequest)
    {
        Integer size = hotelSearchRequest.getPageSize() == null ? 10 : hotelSearchRequest.getPageSize();
        Integer page = hotelSearchRequest.getPage() == null ? 0 : hotelSearchRequest.getPage();
        String name = hotelSearchRequest.getName() ;
        String address = hotelSearchRequest.getAddress();
        BigDecimal maxPrice = hotelSearchRequest.getMaxPrice() ;
        HotelStars rating = hotelSearchRequest.getRating();
        Pageable pageable = PageRequest.of(page ,size);


        Specification<Hotel> hotelSpecification = Specification.where(
                HotelSpecification.hasName(name).and(HotelSpecification.hasAddress(address))
                        .and(HotelSpecification.hasMaxPrice(maxPrice))
                        .and(HotelSpecification.hasRating(rating))
        );
        List<Hotel> hotels = hotelRepository.findAll(hotelSpecification,pageable).getContent();
       return hotelMapper.toDto(hotels);
    }

    @Override
    public List<HotelResponse> findAll() {
       List <Hotel> hotels =  hotelRepository.findAll();
       return  hotelMapper.toDto(hotels) ;
    }

    @Override
    public HotelResponse findById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Hotel id not found"));
        return hotelMapper.toDto(hotel) ;
    }

    @Override
    @Transactional
    public HotelResponse create(AddHotelRequest addHotelRequest)
    {
        if (hotelRepository.findByName(addHotelRequest.getName()) !=null)
        {
            throw new AlreadyExistException("Hotel is already saved before");
        }
        Hotel hotel = hotelMapper.toEntity(addHotelRequest);
        hotel.setRating(HotelStars.FIVE_STAR);
        return hotelMapper.toDto(hotelRepository.save(hotel))   ;

    }

    @Override
    @Transactional
    public void update(Long id, UpdateHotelRequest updateHotelRequest)
    {
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(()->new NotFoundException("Hotel id not found"));      ;
        try
        {
            hotelMapper.update(updateHotelRequest , hotel);
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            throw  new AlreadyExistException("the hotel name is already exist ") ;
        }
        hotelRepository.save(hotel);
    }
}
