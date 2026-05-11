package com.example.hotelmanagmentsystem.repository;

import com.example.hotelmanagmentsystem.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> , JpaSpecificationExecutor<Hotel>
{
    public Hotel findByName (String name) ;

}
