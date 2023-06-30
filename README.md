# PitputitAndroid 


**Android project:**

Targil 4 -
In this exercise, we create an android for our chat system.

####The others "Targilim" are in the following link repository:
```
 https://github.com/nshakedsl/Pitputit.git
 ```

Targil 3 (milestone 2) is on branch milestone2-
In this exercise, we transformed our static client code into a dynamic one by implementing a web server.

Targil 2 (Milestone 1 B) is on branch Milestone1b-
This project is a React App for chatting between different users. We called the website Pitputit, which is similar to the Hebrew word for "to chat" or "to talk".

Targil 1 (Main) is on branch Main-
This reposetory, is a web user interface using HTML and CSS.

## Authors:
1. Shaked Solomon
2. Ofir Gurvits
3. Naama Matzliach

This reposetory, is a React App.
There are 3 pages:
1. SignUp page.
2. Login page.
3. Chats page.

There are 2 more pages have been added in the Android project
1. Settings page
2. Contacts page

##### Please upload low quality images otherwise the pictures will not upload properly


This project is created in Android and React App for chatting between different users. We called the website Pitputit, which is similar to the Hebrew word for "to chat" or "to talk".



## Cloning and Running Instructions:


### 1. run server:

Before running the client application, it is essential to set up and run the server. Please follow the server instructions `before` you run the current program:
Go to the link below and follow the instructions found in the server's git code to run it

    ```
    https://github.com/nshakedsl/pitputitServer.git
    ```
### 2. To clone Andorid repository, follow these steps:

In the terminal, navigate to the directory where you want to clone the repository.

Enter the following command:
```
clone https://github.com/nshakedsl/Pitputit3.git
```

### 4. To run the program:


1. To proceed with the setup, please follow the steps below:

2. Download Android Studio from the official website.
3. Open the folder that you cloned into using Android Studio.
4. Ensure that the build process is executed and that the synchronization is completed correctly. You can do this by opening the Gradle Script.
5. Navigate to the build.gradle page (Module: app) and initiate the synchronization process.
6. Run the project either using an emulator or a physical mobile phone.

#### Please note that when running the project on a physical mobile phone, you will need to locate the IP address of your computer. This IP address you can be updated in the SplashScreen page -row 34 or within the application's settings page.

7. Then you can create users and use the application. 😊


### The assignment parts:
#### In the first part:
We modified the React code to enable communication with a built-in server.
This step served as preparatory code for the subsequent parts.

#### In the second part:
We developed a web server using Node.js.
As a result, when any input is entered through the login, the details are saved.
We implemented a Node.js server following the MVC architecture, which offers the same functionality as the server in the first part. Additionally, it performs additional validation checks to ensure that the entered details are correct before saving them to MongoDB.

#### In the third part:
We established real-time communication.
Users can log in through the login screen and send messages to each other while they are both logged in on their respective browsers. The recipient user immediately receives and sees the message without needing to request it.

#### In the fourth part:
We have developed an Android project that encompasses various functionalities, such as registration pages, chat connectivity, and settings customization. These settings include options to modify user addresses, switch to Dark Mode, and receive notifications from Firefox. All of these features are integrated with the server, enabling seamless communication and data exchange.### Switching Between Pages

To move between the pages, click on "Not registered? Click here to register" on the Login page to access the register/signUp page. To go from the Login page to the Chats page, click on the Log in button after you entered the password and username you signed up with. 

## Pages
### SignUp page:
On the registration screen, users can enter a username, password, nickname, and picture. There is also an option to go to the login screen by clicking on "Already registered? Click here to login."

### Login page:
On the login screen, users can enter a username and password. There is a place to upload a picture. There is also an option to go to the signup screen by clicking on "Not registered? Click here to register."

### Chats page:
The chat screen is divided into two parts. In the left part of the screen, users can see a list of all the chats they are currently engaged in. In the right part of the screen, the correspondence is displayed, including messages with the selected recipient.


## Design -css and boostrap:
The design of the user interface was created using BOOTSTRAP and CSS. Images and additional CSS files can be found in the "public/images" and "Style" folders, respectively.


# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)