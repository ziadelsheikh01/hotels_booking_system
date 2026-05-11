package com.example.hotelmanagmentsystem.dto.hotel;

import com.example.hotelmanagmentsystem.enums.HotelStars;

import java.math.BigDecimal;

public class HotelSearchRequest
{
    private String name ;

    private BigDecimal maxPrice ;

    private HotelStars rating ;

    private  String address ;

    private  Integer page ;
    private  Integer pageSize ;

    public HotelSearchRequest() {
    }

    public HotelSearchRequest(String name, BigDecimal maxPrice, HotelStars rating, String address, Integer page, Integer pageSize) {
        this.name = name;
        this.maxPrice = maxPrice;
        this.rating = rating;
        this.address = address;
        this.pageSize = pageSize;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public HotelStars getRating() {
        return rating;
    }

    public void setRating(HotelStars rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "HotelSearchRequest{" +
                "name='" + name + '\'' +
                ", maxPrice=" + maxPrice +
                ", rating=" + rating +
                ", address='" + address + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
