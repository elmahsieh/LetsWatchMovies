# README
This project called Let’sWatchTV. It is an application that helps users explore movies. This application populates data from three sources 1) API 1 - upcoming movies 2) API 2 - top rates movies 3) JSON file - local data retrieved through API for popular movies. 

### General Information
***The Home Screen*** (landing page) has a “Welcome to Let’s Watch TV” message, along with an animated image, and 4 buttons to press 1) Notepad 2) Explore Movies 3) App Information 4) Settings. 

***Notepad*** leads the user to a notepad/text box where they can write down comments and save to their application. 

***Explore Movies*** leads the user to a Movies List page, with the JSON data populated onto the screen using list view, and two buttons 1) Top Rated Movies 2) Upcoming Movies that will lead users to the according API populated screen. All screens that display movies allow users to click into individual movies to see further details (6 data points) 1) movie poster 2) title 3) release date 4) detail overview 5) language 6) vote average. In the movie details screen, the user is able to heart/un-heart the movie, which stays consistent through the application. The user can additionally click on Rate The App, which demonstrates a dialog with the user and three user options. 

***App Information*** displays the application information through presenting BuildConfig. There’s a total of 6 data points 1) Application Name 2) Version Control 3) Build Date 4) Build Language 5) Version Name 6) Version Code. 

***Settings*** leads the user to a toggle button that can switch API on/off. When the API is on, the user is able to access Top Rated and Upcoming Movies; when the API is off, the two buttons aren’t clickable, hence the data is inaccessible; however, the user is able to see the data populated from the JSON file in Movies List. Additionally, the user can use radio buttons to switch the animation on/off in the application. 

### Features
1. Heart movies: like or dislike movie. 
2. Loaders: progress bar showing the progress the data is being loaded through APIs. 
3. Note Pad: enables users to take notes and comments on movies. 

### API Information

**API from The Movies Data Base (TMDB: https://www.themoviedb.org)**
API 1. Top Rated Movies: https://api.themoviedb.org/3/movie/top_rated
API 2. Upcoming Movies: https://api.themoviedb.org/3/movie/upcoming
Personal API Key if necessary (not required to load LetsWatchTV): f30a516df866e8e39ffc019d1fcace2f

### Application Layout and Navigation
<img width="853" alt="Screenshot 2024-04-29 at 9 38 08 PM" src="https://github.com/elmahsieh/LetsWatchMovies/assets/141378765/321602c0-060e-4247-8759-a7a22a249ed6">

### Application Images
<img width="295" alt="Screenshot 2024-05-16 at 2 25 26 PM" src="https://github.com/elmahsieh/LetsWatchMovies/assets/141378765/f148f73a-792c-42ee-996a-128654eecfb7">
<img width="294" alt="Screenshot 2024-05-16 at 2 25 51 PM" src="https://github.com/elmahsieh/LetsWatchMovies/assets/141378765/7b18f5e3-7f35-4c3e-9947-14b2a0a2f478">