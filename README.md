# Testinium Automation - API Test Project

## Overview
This project is an API test automation framework for Trello, built using Java and TestNG with Maven. It includes API tests to validate Trello's functionalities.

## Prerequisites
Ensure you have the following installed:
- Java 8 or later
- Apache Maven
- IDE (IntelliJ IDEA, Eclipse, or any preferred Java IDE)

## Installation
1. Clone the repository:
   ```sh
   git clone <repository_url>
   ```
2. Navigate to the project directory:
   ```sh
   cd Testinium-Automation/test-v2
   ```
3. Install dependencies using Maven:
   ```sh
   mvn clean install
   ```

## Configuration
- Set up the `.env` file with the required API credentials.
- Update `src/main/resources/config.properties` for test configurations.

## Running Tests
To execute tests, use:
```sh
mvn test
```
Logs are stored in `logs/app.log`.

## Project Structure
```
Testinium-Automation/
  ├── test-v2/
  │   ├── src/main/java/com/example/trello/       # API client
  │   ├── src/test/java/com/example/trello/       # Test cases
  │   ├── logs/                                   # Logs directory
  │   ├── pom.xml                                 # Maven configuration
  │   ├── .env                                    # Environment variables
  │   ├── .idea/                                  # IDE configuration files
```

## Reporting
After running tests, reports will be generated in `target/surefire-reports`.

## License
This project is licensed under the MIT License.

## Contributing
Feel free to submit issues and pull requests.
