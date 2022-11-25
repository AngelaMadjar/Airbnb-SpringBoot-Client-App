package com.angelamadjar.airbnbproject.config;

import com.angelamadjar.airbnbproject.model.enumerations.ListingObjectType;
import com.angelamadjar.airbnbproject.model.enumerations.RoomTypeCategory;
import com.angelamadjar.airbnbproject.service.interfaces.AccommodationService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final AccommodationService accommodationService;


    public DataInitializer(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    // initializing the database
    //@PostConstruct
    public void initData() {
        this.accommodationService.create("Trullo Unico",
                "Alberobello",
                ListingObjectType.REGULAR,
                "Trullo",
                RoomTypeCategory.entire_home,
                "4",
                1.00,
                1, 1,
                true,
                "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/7d/c8/a9/20190216-132353-largejpg.jpg?w=1200&h=1200&s=1",
                140.00);

        this.accommodationService.create("Beautiful Villa",
                "Milan",
                ListingObjectType.REPRESENTATIVE,
                "Villa",
                RoomTypeCategory.entire_home,
                "5",
                2.00,
                3, 3,
                true,
                "https://venue-media.eventup.com/resized/venue/villa-milano-banquet-conference-center-2/6d53.1920x1080.png",
                305.00);


        this.accommodationService.create("House of Genoa",
                "Genoa",
                ListingObjectType.REGULAR,
                "Home",
                RoomTypeCategory.entire_home,
                "2",
                1.00,
                2, 2,
                false,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTt7Q9B3vRVBlEIsRWKpP9e2isX_X2W3VDSOokJuKGDyin7fHrv-515SCWlK5kngiVn508&usqp=CAU",
                90.00);

        this.accommodationService.create("L'Aquila Hotel",
                "L'Aquila",
                ListingObjectType.REGULAR,
                "Hotel room",
                RoomTypeCategory.hotel_room,
                "2",
                1.00,
                1, 1,
                false,
                "https://cf.bstatic.com/xdata/images/hotel/max1024x768/154655774.jpg?k=c9db7cc405a2c38d750b89f4ce2263c726ee09d2e8060204b6f0e3f6bfd51fa1&o=&hp=1",
                65.00);

        this.accommodationService.create("Best of Positano",
                "Positano",
                ListingObjectType.REPRESENTATIVE,
                "Loft",
                RoomTypeCategory.entire_home,
                "2",
                1.00,
                1, 1,
                true,
                "https://cf.bstatic.com/xdata/images/hotel/270x200/21584798.jpg?k=3e1c7198a4a7b39f2e778372749c2082116c05cfdda552c652866dcac624fe50&o=",
                165.00);

        this.accommodationService.create("Old Matera",
                "Matera",
                ListingObjectType.REGULAR,
                "Tiny House",
                RoomTypeCategory.entire_home,
                "4",
                1.00,
                2, 2,
                false,
                "https://cf.bstatic.com/xdata/images/hotel/270x200/26137195.jpg?k=3bbd56cef6cc45684ccb8ceee7e8c9de8e5b8f92e9ba4fe07001bbad85e2b1f4&o=",
                100.00);

        this.accommodationService.create("Ancona Harbor",
                "Ancona",
                ListingObjectType.REGULAR,
                "Apartment",
                RoomTypeCategory.private_room,
                "2",
                1.00,
                1, 2,
                true,
                "https://asset1.zankyou.com/images/wervice-card/3bd/3b6c/400/312/w/703507/-/1556542275.jpg",
                55.00);

        this.accommodationService.create("City of Bari",
                "Bari",
                ListingObjectType.REGULAR,
                "Apartment",
                RoomTypeCategory.private_room,
                "2",
                1.00,
                1, 2,
                true,
                "https://cf.bstatic.com/xdata/images/hotel/max1024x768/349980075.jpg?k=b11e7cd092b9affbd75e3b23a247852f6abb2c0f2438f9edee5e909a17d081ae&o=&hp=1",
                75.00);
    }
}
