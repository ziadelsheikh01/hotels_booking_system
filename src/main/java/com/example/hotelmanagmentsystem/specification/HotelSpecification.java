package com.example.hotelmanagmentsystem.specification;

import com.example.hotelmanagmentsystem.entity.Hotel;
import com.example.hotelmanagmentsystem.enums.HotelStars;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class HotelSpecification
{

    public  static Specification<Hotel>hasName (String name)
    {
        return  ((root, query, criteriaBuilder) ->
                name == null ? null :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")) ,"%" + name.trim().toLowerCase() + "%")
                ) ;
    }


    public  static  Specification<Hotel> hasMaxPrice(BigDecimal maxPrice)
    {
        return ((root, query, criteriaBuilder) ->
                maxPrice == null ? null :
                        criteriaBuilder.lessThanOrEqualTo(root.get("price") , maxPrice)
                );
    }

    public  static  Specification<Hotel> hasAddress (String address)
    {
        return  ((root, query, criteriaBuilder) ->
                address == null ? null :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("address")) , "%" + address.toLowerCase() + "%" )
        ) ;
    }

    public  static  Specification<Hotel> hasRating (HotelStars stars)
    {
        return ((root, query, criteriaBuilder) ->
                stars == null ? null :
                        criteriaBuilder.equal(root.get("rating"),stars)
                );
    }
}
