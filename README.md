# Airbnb SpringBoot Client App

This is a simple Spring Boot application coded in Java. It follows the onion architecture style (Domain, Repository, Service and Presentation layers) 
and encapsulates the MVC design pattern in the Presentation layer).

The idea was to create an Accommodation Price Estimator that will predict the appropriate price of the accommodation depending on its characteristics
(the city its located in, the average rating etc.).

## Functionalities:

- It enables CRUD operations on Accommodation entities persisting in a PostgreSQL database. 
- It communicates with the service available in the following repository: https://github.com/AngelaMadjar/Airbnb-ETL-PricePredictor. 
  It populates a Data Transfer Object with the data provided by the user on the frontend side and sends it as a JSON object to the ML model 
  exposed as an API on the following URL:  http://127.0.0.1:5000/predict.
  The response is then displayed to the user.
  
  
## Simple Project Schema: 

![image](https://user-images.githubusercontent.com/74113692/203972987-0baad669-3d9b-460c-bc4a-2832662de6aa.png)


## Demo:

### Home Page
![image](https://user-images.githubusercontent.com/74113692/203975657-e7a551be-a46d-48b9-8b1f-44104615573e.png)


### Accommodations Page
![image](https://user-images.githubusercontent.com/74113692/203975870-3d726578-5717-4786-8234-01319d435af8.png)

### Accommodations Persisting in a PostgreSQL database
![image](https://user-images.githubusercontent.com/74113692/203975973-764e5ba2-ea4a-4097-8851-57effb2f12ef.png)

### Price Estimator
![image](https://user-images.githubusercontent.com/74113692/203976256-a30438ec-e5e5-47b3-bc65-31d0e27a8304.png)



## Future improvements:
- Add authentication with Spring Security
- Add authorization on different runctionalities and create two roles: Admin and User
- Enable the Users to give reviews on the accommodations
- Create a sepparate Review entity and set one-to-many relationship between an accommodation and reviews
- Improve frontend design :)
