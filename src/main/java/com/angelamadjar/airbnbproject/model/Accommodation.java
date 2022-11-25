package com.angelamadjar.airbnbproject.model;

import com.angelamadjar.airbnbproject.model.enumerations.ListingObjectType;
import com.angelamadjar.airbnbproject.model.enumerations.RoomTypeCategory;
import javax.persistence.*;


// Models are mapped to tables in the database
// This is enabled with the Entity annotation

@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // the id is incrementally increased when adding accommodation to the database

    private String name;
    private String city;
    private Double avgRating;
    private Double starRating;
    private Integer reviewsCount;

    // enum type
    @Enumerated(value = EnumType.STRING)
    private ListingObjectType listingObjType;

    private String spaceType;

    // enum type
    @Enumerated(value = EnumType.STRING)
    private RoomTypeCategory roomTypeCategory;

    private String listingGuestLabel;
    private Double bathrooms;
    private Integer bedrooms;
    private Integer beds;
    private Boolean isSuperHost;
    private String imageUrl;
    private Double price;

    // Constructor
    public Accommodation(String name, String city, ListingObjectType listingObjType, String spaceType, RoomTypeCategory roomTypeCategory, String listingGuestLabel, Double bathrooms, Integer bedrooms, Integer beds, Boolean isSuperHost, String imageUrl, Double price) {
        this.name=name;
        this.city = city;
        this.listingObjType = listingObjType;
        this.spaceType = spaceType;
        this.roomTypeCategory = roomTypeCategory;
        this.listingGuestLabel = listingGuestLabel;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.beds = beds;
        this.isSuperHost = isSuperHost;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Default constructor
    public Accommodation() {}


    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Double getStarRating() {
        return starRating;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }


    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public String getListingGuestLabel() {
        return listingGuestLabel;
    }

    public void setListingGuestLabel(String listingGuestLabel) {
        this.listingGuestLabel = listingGuestLabel;
    }

    public Double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Boolean getSuperHost() {
        return isSuperHost;
    }

    public void setSuperHost(Boolean superHost) {
        isSuperHost = superHost;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ListingObjectType getListingObjType() {
        return listingObjType;
    }

    public void setListingObjType(ListingObjectType listingObjType) {
        this.listingObjType = listingObjType;
    }

    public RoomTypeCategory getRoomTypeCategory() {
        return roomTypeCategory;
    }

    public void setRoomTypeCategory(RoomTypeCategory roomTypeCategory) {
        this.roomTypeCategory = roomTypeCategory;
    }
}
