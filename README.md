# Selenium-RESTASSURED-ExtentReport Framework for Openweather App Automation Testing

## Table of Contents
-[Application overview](#openweatherapi)

-[Microservices Covered](#microservices-covered)

-[About Test Suite](#about-test-suite)

-[How to run this project](#how-to-run-this-project)
    -[Prerequisite](#prerequisite)
    -[Commandline test execution in local](#commandline-test-execution-in-local)
    -[How to set up this project in eclipse](#how-to-set-up-this-project-in-eclipse)

-[Reports](#reports)
    -[Report format](#report-format)
    -[Report generated in Github action](#report-generated-in-github-action)

## OpenWeatherAPI:
Using Openweather App we can get essential weather data, short-term and long-term forecasts and aggregated weather. One Call API 3.0 is based on the proprietary OpenWeather Model and is updated every 10 minutes
Link--> https://openweathermap.org/api/one-call-3

## Microservices Covered
Weather Retrieval: Tests related to retrieving weather data using the OpenWeatherMap API

## About Test Suite
The test suite is divided into the following categories:

Positive Tests: Verify the correct functionality of the API under valid conditions.
Negative Tests: Verify the API's behavior under invalid conditions.

## How to run this project
The test suite is divided into the following categories:

### Prerequisite

->Maven installation- https://maven.apache.org/install.html

->Java installation- https://www.java.com/en/download/help/download_options.html

-->Git installation- https://github.com/git-guides/install-git

### Commandline test execution in local
    
    1. Clone the Repository
    git clone https://github.com/saradhaa37/Chip-AutomationTesting.git
    cd Chip-AutomationTesting
    
    2. Set Up Configuration
    Add your valid OpenWeatherMap API key in the Weatherdata.json file located in src/main/resources/Data/.
    Run the Tests
    
    3. Execute the test
    mvn clean install
    mvn test

### How to set up this project in eclipse

->Import projetc->smart import

->provide the ssh code and finish import

## Reports:

### Report format
Once the test is executed successfully in local report will be created under test-output folder => test-output/ExtentReports_All.html like below,

![image](https://github.com/saradhaa37/Chip-AutomationTesting/assets/72251600/ccc6a666-6454-4b03-af57-fcb65abe8e4a)

### Report generated in Github action
Once the test is completed in github action , report will be generated under respective job summary page like below under Artifact section

<img width="1512" alt="image" src="https://github.com/saradhaa37/Chip-AutomationTesting/assets/72251600/31aed711-71ad-493c-931f-c2bb176bfcfc">
