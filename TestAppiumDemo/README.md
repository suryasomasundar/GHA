# **Mobile App Testing Setup Guide**

This guide provides step-by-step instructions for setting up and running mobile app tests on your local machine. Follow
these steps to get started with mobile app automation.

---
## **Key Changes to Make in the Script**

This section is for users who already have the necessary setup (Appium, emulators, dependencies, etc.) configured on their machine. If you are setting up on a fresh machine, follow the [Installation Guide]: #installation-steps.

Before running the tests, ensure the following configurations are updated as per your environment:

### **1. Desired Capabilities**
- Update the `src/main/resources/config.properties` file with the necessary configurations for your devices and platforms.

### **2. Appium Server Port**
- By default, the tests run on port `4723` (the default Appium server port).
- If you change the port, update it in `src/test/java/utils/AppiumDriverManager.java` (Line 50).

### **3. Android Emulator**
- Ensure the Android emulator is up and running.
- By default, the script uses the emulator with the device ID `emulator-5554`.
- Update this in the configuration if your emulator uses a different ID.

### **4. iOS Simulator**
- Ensure the iOS simulator is running.
- Update the `ios.platformVersion` and `ios.deviceName` in the configuration files to match your simulator's version and device name.

### **5. Execution**
- Once the above configurations are updated, follow the instructions below to execute the tests.
- Happy testing! Review the execution logs and results for insights.

# **[Installation Guide]()**
## **Prerequisites**

Before you begin, ensure the following are installed on your system:

1. **Git**: To clone the repository.
2. **Homebrew**: To install dependencies (macOS only).
3. **Xcode**: For iOS testing (macOS only).
4. **Android SDK**: For Android testing.
5. **Java Development Kit (JDK)**: Required for Maven.
6. **Node.js and npm**: Required for Appium.
7. **Allure CLI**: For generating test reports (optional but recommended).

---

## **Step 1: Clone the Repository**

1. Open your terminal.
2. Run the following command to clone the repository:
   ```bash
   git clone https://github.com/suryasomasundar/mobile-app-tests.git

## **Step 2: Navigate to the Project Directory**

1. Change to the project directory:
   ```bash
   cd mobile-app-tests/TestAppiumDemo

## **Step 3: Install Dependencies*

1. Install Maven:
   ```bash
   brew install maven 

2. Install Node.js and npm
   ```bash
   brew install node

3. Install Appium
   ```bash
   npm install -g appium

4. Install WebDriverAgent
   ```bash
   git clone https://github.com/appium/WebDriverAgent.git ~/WebDriverAgent

## **Step 4: Set Up Emulators/Simulators*

**For iOS**:

1. Open Xcode and ensure the iOS simulator (iPhone 16 with iOS 18.3) is available
2. If not, create a new simulator using Xcode.
    - Open Xcode > Window > Devices and Simulators.
    - Add a new simulator with the desired device and iOS version.

**For Android**:

1. Open Android Studio and ensure the Android emulator (emulator-5554) is available.
2. If not, create a new emulator using Android Studio.
    - Open Android Studio > Tools > AVD Manager.
    - Add a new virtual device with the desired configuration.

## **Step 5: Start Appium Server**

1. Open a terminal window.
2. Start the Appium server:
   ```bash
   appium

- Make sure the server runs on the default port 4723

## **Step 6: Run the Tests**

1. Navigate to the project directory
   ```bash
   cd mobile-app-tests/TestAppiumDemo

2. Run the tests using Maven
   ```bash
   mvn clean test

- This will execute all the tests both iOS and Android

## **Step 7: Generate and View Test Reports**

1. Install Allure CLI (if not already installed):
   ```bash
   brew install allure
2. Generate the Allure report:
   ```bash
   allure generate allure-results --clean -o allure-report

- Generates a nice html report under allure-report folder


## **Troubleshooting**  
1. Ensure the WebDriverAgent is located at ~/WebDriverAgent. If not, clone it again
2. Ensure port 4723 is running. If you use a different port update the script
3. Verify the device names and versions match your environment.Check for the available device and update the script
4. Run -- brew doctor to diagnose Homebrew issues
5. Ensure the Appium server is running and the emulator/simulator is booted







