package com.angelamadjar.airbnbproject.model;

// This is a Data Transfer Object used to pass the necessary data to the Price Predictor microservice
// This is not an Entity and it is not stored to a database

public class PricePredictionDTO {

      private String city;
      private Double avgRating;
      private Integer reviewsCount;
      private String listingObjType;
      private String spaceType;
      private String roomTypeCategory;
      private String listingGuestLabel;
      private Double bathrooms;
      private Integer bedrooms;
      private Integer beds;
      private String checkIn;
      private Boolean isSuperHost;

      // constructor
      public PricePredictionDTO(String city, Double avgRating,
                                Integer reviewsCount, String listingObjType,
                                String spaceType, String roomTypeCategory, String listingGuestLabel,
                                Double bathrooms, Integer bedrooms, Integer beds,
                                String checkIn, Boolean isSuperHost) {
            this.city = city;
            this.avgRating = avgRating;
            this.reviewsCount = reviewsCount;
            this.listingObjType = listingObjType;
            this.spaceType = spaceType;
            this.roomTypeCategory = roomTypeCategory;
            this.listingGuestLabel = listingGuestLabel;
            this.bathrooms = bathrooms;
            this.bedrooms = bedrooms;
            this.beds = beds;
            this.checkIn = checkIn;
            this.isSuperHost = isSuperHost;
      }

      // default constructor
      public PricePredictionDTO() {}

      // getters and setters
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

      public Integer getReviewsCount() {
            return reviewsCount;
      }

      public void setReviewsCount(Integer reviewsCount) {
            this.reviewsCount = reviewsCount;
      }

      public String getListingObjType() {
            return listingObjType;
      }

      public void setListingObjType(String listingObjType) {
            this.listingObjType = listingObjType;
      }

      public String getSpaceType() {
            return spaceType;
      }

      public void setSpaceType(String spaceType) {
            this.spaceType = spaceType;
      }

      public String getRoomTypeCategory() {
            return roomTypeCategory;
      }

      public void setRoomTypeCategory(String roomTypeCategory) {
            this.roomTypeCategory = roomTypeCategory;
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

      public String getCheckIn() {
            return checkIn;
      }

      public void setCheckIn(String checkIn) {
            this.checkIn = checkIn;
      }

      public Boolean getSuperHost() {
            return isSuperHost;
      }

      public void setSuperHost(Boolean superHost) {
            isSuperHost = superHost;
      }
}
