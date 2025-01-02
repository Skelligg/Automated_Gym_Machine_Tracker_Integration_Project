# Easy Rep

## Members:
- Philipe Souza
- Michael Lukyanov
- Anna Mestres
- Martin Ivanov
- Ben Lancry

## Installation and Configuration Instructions:
In order to run the code locally, you have two possible options:

1. **Cloning the project** from https://gitlab.com/kdg-ti/integration-2.1/24-25/team-13/gym-accessory-project and opening the project using your favorite IDE. It's important to be connected to the database by setting (if not already set) those fields in `application.properties`:
    ```
    spring.datasource.url=jdbc:postgresql://10.134.178.163:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=IP3dbP@ss
    ```
   The code now can be run using the class `StartApplication`.

2. **Accessing the webserver** `10.134.178.163:80`.
   From there, you can simply create an account and profit from all the functionalities available on the application for a gymGoer.

## Dependencies:
- **org.springframework.boot:spring-boot-starter-validation**:
  Used to create validation on the ViewModels, adding constraints to variables.

- **org.springframework.boot:spring-boot-starter-thymeleaf**:
  Framework that connects the Java Spring code with our HTML.

- **org.springframework.boot:spring-boot-starter-web**:

- **org.springframework.boot:spring-boot-starter-jdbc**:
  Responsible for accessing data stored in the PostgreSQL relational database.

- **org.springframework.boot:spring-boot-starter-data-jpa**:
  Using the `JPARepositories` allows the Java Spring project to connect to the database with a minimum amount of code, since JPA provides access to written code for certain methods.

- **org.webjars:bootstrap:5.3.2**:
  Bootstrap was used to style the HTML pages.

- **org.postgresql:postgresql**:
  PostgreSQL was the chosen SQL database to work on this project.

- **org.json:json:20210307**:
  JSON was used to communicate to Python when using certain predictions on the project, such as likelihood of a user giving up by gender.

- **org.tensorflow:tensorflow-core-platform:0.5.0**:
  TensorFlow was used to predict the amount of weight a user will be able to lift depending on how much they have lifted in their previous workouts.

## Other Systems:
For this project to work, Arduino was the main device counting the repetitions and the weight lifted using sensors. More on how the Arduino was used can be found in the file `arduino.md`.
