# HotelBooker
Hotel Booker is a prototype for a hotel reservation mobile application. The purpose of
the Hotel Booker mobile application is to allow a user to reserve prominent hotels in
and around the United Kingdom. The features are as follows:
- Registration of a user
- Authentication and Authorisation system
- Search/Filter hotels
- Reserve a hotel
- View all reservations made by the logged in user
- Data Persistence

##### User Registration
In order to interact with the application, the user is required to register information
such as their name, email and password. The email and password is necessary so that
the user may be allowed to log in whereas the other information is used for other
functionalities of the application. Error/Exception handling will also be in place to
avoid human errors (e.g missing ‘@’ for email, blank fields, etc...) aswell as to
prevent the application from crashing.

##### Authentication and Authorisation System
The application will disallow the user from any interactions (apart from logging in
and registration) unless the user is logged in. When registering their details, a user
will have set an email address and a password. These details are then saved for
authentication purposes.

The authentication system will take in an email address input and password input.
The inputs are then compared to the data. The condition for a successful login is that
both, the email address exists, and the password pertaining to that email address is
matching. 

If both conditions are not fulfilled, an error message is returned and the
user is prompted to try again. If both conditions are fulfilled, the system authorises
the user to allow for further interactions with the application. The user is then logged
in and granted all the above-mentioned privileges (features). Unless logged out,
depending on the use

##### Search/Filter
Search/Filter
The list of hotels may be exhaustively long and to prepare for such a case, a
search/filter feature will be in place. This will reduce the list to only hotels matching
the user’s search criteria. The planned criterion’s are:
- Hotel Name
- City
- Rating

The hotel name and city will take a string input and search for hotels containing that
string. Whereas the rating will take an integer and return hotels only matching that
integer.

##### Hotel Reservation
The user will be presented with a list of hotels. From the list, the user will be able to
select and book a hotel. The user will be presented with various fields to fill out
(check-in date, how many nights, how many guests). Depending on the user input and
the hotel selected, the system will then return a calculated price. The user will then
have the option to either cancel or confirm the reservation. If the former is selected,
the user can make changes to the reservation or go back. If the latter is selected, the
reservation is saved to the database and the user is redirected to the main activity.

##### View Reservations
The system will allow the user to view a list of all reservations made. The list will
only contain reservations made by the currently logged in user. The list be be
formatted as such:
{hotelName}
{hotelAddress}
{checkInDate} {noOfNights} {noOfGuests}
{totalCost}

##### Data Persistence
In order for the application to function, different sort of data is taken and saved at
different steps. For example:
- Registration – takes in user name, email and password
- Hotel – hotel will have a name, address, phone number and rating
- Reservations – who made the reservation, date of check-in, nights and guests

It is futile if the data is diminished upon exiting the application. Therefore the plan is
to use SQLite database to store and retrieve data.


<img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/main_activity.jpg?raw=true" width="250"><img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/register.jpg?raw=true" width="250"><img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/login.jpg?raw=true" width="250"><img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/main_activity2.jpg?raw=true" width="250">
<img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/hotel_list.jpg?raw=true" width="250"><img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/search.jpg?raw=true" width="250"><img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/search_result.jpg?raw=true" width="250"><img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/book.jpg?raw=true" width="250">
<img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/book_confirm.jpg?raw=true" width="250"><img src="https://github.com/SuparnoD/HotelBooker/blob/master/app/src/main/res/drawable/res_list.jpg?raw=true" width="250">

