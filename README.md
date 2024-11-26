# My Final Project

This project is the final stage of my automated testing course. It focuses on automating the testing of the search field functionality on the Skillo training website (URL:http://training.skillo-bg.com:4300/posts/all).
The search field plays an important role in the platform, allowing users to search for other users and interact with them. This project aims to demonstrate that the field works correctly under different conditions, adheres to usability standards, and follows its logical flow.
The implementation follows industry best practices, including the Page Object Model (POM) and Page Factory patterns, ensuring easier maintenance and readability. In addition, the project uses tools such as Maven and Selenium WebDriver for automation and test execution.

## Technologies Used
Java is the main programming language for this project.
Maven is used for dependency management and project configuration, with the pom.xml file containing dependencies for Selenium, TestNG, and WebDriverManager.
The target browser for testing is Google Chrome.

### Test Artifacts:

Screenshots are automatically taken during failed tests and stored in the resources folder for debugging purposes.

TestNG XML configuration - The project includes a testng.xml file that is used to define the test execution flow, group tests, and manage test suites. This allows greater flexibility in executing tests or test sets without modifying the code.

## Project Execution Details
The project is structured around the Page Object Model and Page Factory, with individual classes for each major page of the website. Each class contains methods specific to that page or part of it.

### Login Page:
Contains methods for entering login credentials (email/username and password) and submitting the login form.
Supports test initialization by logging into the website before executing the main test steps.

### Header:
Contains methods for interacting with the search field.
Includes reusable methods for locating the search field element, entering text, retrieving text from the field and the dropdown, accessing elements within the dropdown, and interacting with them.

### User Profile Page:
Includes a validation method to confirm that the user is redirected to the correct user profile page after interacting with the search results.

### Home Page:
Contains methods to check the successful loading of the homepage by validating the URL.

### TestObject Class:
Using Before and After annotations, the class contains methods for:

**Driver Setup:** Initializes WebDriver and browser settings.

**Teardown Methods:** Ensures browser sessions are closed after test execution.

**Screenshots:** Captures images of the browser state during failed tests and removes them before re-running tests.

**Data Providers:** Supplies test data using two-dimensional arrays, allowing data to be used for repeatable testing.

## Challenges Encountered:

Dynamic Elements and StaleElementReferenceException:
The dynamic nature of the dropdown menu with search suggestions posed a significant challenge. StaleElementReferenceException frequently occurred due to the DOM structure and updates while interacting with elements, rendering previously located elements invalid and causing subsequent interactions to fail. The suggestions were dynamically updated after a search query was entered. To handle this, I implemented a method called "refreshed." This helper method wraps an existing Selenium ExpectedCondition, ensuring it can handle situations where the DOM has changed, causing the StaleElementReferenceException. This approach allowed the page to perform an “internal” refresh before test actions were resumed, significantly improving test reliability. However, the error still occurs occasionally. When the same test is executed repeatedly, the dropdown suggestions appear faster or slower due to backend or network delays. Adjusting wait times and using the refresh method helped mitigate the issue, but achieving complete consistency remains a challenge, and the problem is not fully eliminated.

