# Welcome to the Online Library System Repository
This is the repository of the Online Library System that provides a web frontend to residents of a small town to interact with the library remotely and to librarians to manage the library. As a group of six software engineers, we are developing a fullstack software following an Agile development workflow. The development of the system is divided into four sprints called deliverables. The first deliverable is to create the project and define model and the persistence layer through requirements, use-case diagrams, and class diagrams. The second deliverable is implementing the Backend component of the system such as the API. The third and fourth deliverable is to create the website and the application of the system, respectively. You will find further information on the [project's wiki](https://github.com/McGill-ECSE321-Fall2021/project-group-01/wiki) concerning the team's design choices.

## Find out about the Project

Click on the following link: https://onlinelibrarysystem-frontend.herokuapp.com/#/

Use the following Head Librarian account:
- User ID: 11
- Password: adminpass

Or create a new Client account!

## Setup the Project
#### 1. Cloning the project repository
```
git clone https://github.com/McGill-ECSE321-Fall2021/project-group-01.git
```
#### 2. Setting Up the Heroku Backend Heroku App
```
# Assuming that you are in the root of the project
cd ./OnlineLibrarySystem-Backend/

# Create Heroku App
heroku create onlinelibrarysystem-backend-<UNIQUE_ID> -n

# Add the following buildpacks
heroku buildpacks:add -a onlinelibrarysystem-backend-<UNIQUE_ID> https://github.com/heroku/heroku-buildpack-multi-procfile
heroku buildpacks:add -a onlinelibrarysystem-backend-<UNIQUE_ID> heroku/gradle

# Set the PROCFILE
heroku config:add PROCFILE=OnlineLibrarySystem-Backend/Procfile -a onlinelibrarysystem-backend-<UNIQUE_ID>

# Set the Heroku Git URL
heroku git:remote -a onlinelibrarysystem-backend-<UNIQUE_ID> --remote backend-heroku
```
#### 3. Deploying the Backend
```
# If changes were made to the Backend, Follow step 1-3

# 1. Verify that the changes do not fail the build
gradle build

# 2. Add changes to the repository
git add .

# 3. Commit changes to the repository
git commit -m "Your Message"

# 4. Deploy to the Backend Heroku App
git push backend-heroku main
```
#### 4. Setting Up the Heroku Frontend Heroku App
```
# Assuming that you are in the root of the project
cd ./OnlineLibrarySystem-Frontend/

# Create Heroku App
heroku create onlinelibrarysystem-frontend-<UNIQUE_ID> -n

# CLI Installation of the subdir-heroku-buildpack
heroku create --buildpack https://github.com/timanovsky/subdir-heroku-buildpack.git

# Add the following buildpacks
heroku buildpacks:set https://github.com/timanovsky/subdir-heroku-buildpack -a onlinelibrarysystem-frontend-<UNIQUE_ID> 
heroku buildpacks:add -a onlinelibrarysystem-frontend-<UNIQUE_ID> heroku/nodejs

# Set Project Path
heroku config:set PROJECT_PATH=projects/nodejs/frontend

# Set the Heroku Git URL
heroku git:remote -a onlinelibrarysystem-backend-<UNIQUE_ID> --remote backend-heroku
```
#### 5. Deploying the Frontend
```
# If changes were made to the Frontend, Follow step 1-2

# 1. Add changes to the repository
git add .

# 2. Commit changes to the repository
git commit -m "Your Message"

# 3. Deploy to the Backend Heroku App
git push frontend-heroku main
```
### Setup the Android Application
#### 1. Change to the Android-Application branch
```
git checkout Android-Application
```
#### Ensure you have the following inside of AndroidManifest.xml
```
# Add internet permission
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.group01.myapplication">
    <!-- Insert the Line below -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <application ...
```
#### 2. Run the MainActivity.java
Start the MainActivity.java by pressing the run button the Android Studio IDE.

### Running the Application Locally
#### 1. Installing Dependencies
```
# 1. Assuming that you are in the root of the project
cd ./OnlineLibrarySystem-Frontend/

# 2. Create a package.json
npm init

# 3. Install the necessary Packages
npm install

# 3.1 Install packages independently
npm express serve-static
```
#### 2. Running the Frontend Locally
```
npm run dev
```

## Team
### Anika Kabir
- Enjoys eating all sorts of different cuisine
- Has experience within the classes in university
- Interested to learn about the different components that defines the whole software system.
### Chloe Nasrallah
- Cherishes her relationship with her friends
- Has experience within the classes in university
- Interested to work with teammates within the same field.
### Philippe Shi
- Enjoys playing the piano and singing
- Has experience performing web auto testing
- Looks forward to apply his knowledge in web testing into the project.
### Maria Emilia Solaberrieta
- Enjoys dancing
- Has experience within the classes in university
- Looks forward to applying their knowledge of software engineering into an actual project
### Simon Wang
- Enjoys playing video games as a pastime
- Has experience in several development project notably in web development
- Eager to share his experiences to the group and create new ones in the process
### Wen Hin Brandon Wong
- Enjoys playing and watching card games as a pastime
- Has recently started in web development through clubs and as a pastime
- Looks forward to learning to follow an Agile workflow with his teammates

### Hours Table - Deliverable 1
| Team Member | Team Role | Individual Efforts (hours) |
| -- | -- | -- |
| Anika | Client: Reminds the team about the requirements and ensure that each key decisions follow the client's needs | 23 |
| Chloe | NoteTaker: Takes notes of the team's decisions during meetings and update the Minute Meetings on GitHub | 21 |
| Philippe| Participant: Suggests new ideas and share the role of different members notably the Client by ensuring the idea follows the client's need and the Facilitator by keeping the team active throughout the meeting |12|
| Maria Emilia | Facilitator: Ensures that the team achieves the agenda points that were defined by the Leader and that the team's participation remains active throughout the meeting | 20 |
| Simon | Time Keeper: Ensures that the team follows the expected meeting schedule and that influnces the flow of the meeting's conversation | 30 |
| Wen Hin Brandon | Leader: Takes responsibility to ensure that each member succeeds to make the project a functional system following the requirements defined as a team | 30|

### Hours Table - Deliverable 2
| Team Member | Team Role | Individual Efforts (hours) |
| -- | -- | -- |
| Anika | Attending Office hours in order to fix all problems requiring a TA and to ask any other questions; ViewLibraryItems use case & ManageInventory use case service and controller methods; Tests for  ViewLibraryItems & ManageInventory services; documentation related to RESTful API calls | 56 |
| Chloe | Wrote Manage Schedule Service and Controller methods, along with the tests. Also took notes of the minute meetings. | 42 |
| Philippe| Implemented Manage Event Service and Controller methods and wrote their tests.  | 40 |
| Maria Emilia | Wrote Manage Account Use Case Service and Controller methods, and tests | 60 |
| Simon | Wrote Manage Cart Controller and Service Methods, added tests for these methods and wrote tests for View Library Items Use Case | 60 |
| Wen Hin Brandon | Wrote the View and Manage Library Controller and Service Methods, also wrote unit and integration testing for View and Manage Library and Manage Schedule and View Library Items | 60 |

### Hours Table - Deliverable 3
| Team Member | Team Role | Individual Efforts (hours) |
| -- | -- | -- |
| Anika | ViewLibraryItems use case & ManageInventory frontend use case frontends, added everything necessary to implement getByTitle methods in backend (repository, services, controller, tests) | 40 |
| Chloe | Fixed backend services, controller and tests for Manage Schedule methods, and implement frontend for the manage schedule page. | 55 |
| Philippe| Modified Event class model and added service and controller methods, wrote frontend code for Manage Event use case and the description of our architecture model.|40 |
| Maria Emilia | Wrote backend services, controller and tests for login, frontend for login, create, edit and viewing all account types, wrote key design decisions on GitHub | 45 |
| Simon | Wrote frontend for Use Case Scenario, designed architecture model figure, Debugged with teammates extensively | 35 |
| Wen Hin Brandon | Merged all branches to the main file, edit all files to have a consistent styling, fixed controller calls in the frontend, Wrote the frontend for View Library Use Case | 40 |

### Hours Table - Deliverable 4
| Team Member | Team Role | Individual Efforts (hours) |
| -- | -- | -- |
| Anika | Write the documentation of the key features of the application (e.g., Introduction and Head-Librarian View) | 2 |
| Chloe | Write the WorkSlot Services, Controller and View | 20 |
| Philippe | Write the documentation of the key features of the application (e.g., Client View) |3 |
| Maria Emilia | Write the documentation of the key features of the application (e.g., Librarian View) | 2 |
| Simon | Helped out with deployment, debugged with application conversion, added WebView for app | 20 |
| Wen Hin Brandon | Deploy the frontend and the backend on heroku, reviewed and write the documentation on deployment, and the key features of the application. | 15 |
