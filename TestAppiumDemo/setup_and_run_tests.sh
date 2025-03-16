#!/bin/bash

# User-specific variables (can be set as environment variables or passed as arguments)
USER_HOME="$HOME"
REPO_DIR="${USER_HOME}/Documents/CodeCheck/mobile-app-tests/TestAppiumDemo"
WEBDRIVERAGENT_DIR="${USER_HOME}/WebDriverAgent"

# Other variables
REPO_URL="https://github.com/suryasomasundar/mobile-app-tests.git"
APPIUM_PORT=4723
ANDROID_EMULATOR="emulator-5554"
IPHONE_DEVICE="iPhone 16"
IOS_VERSION="18.3"
CUCUMBER_JSON_DIR="$REPO_DIR/target/cucumber-json"
CUCUMBER_JSON_FILE="$CUCUMBER_JSON_DIR/cucumber.json"

# Function to check if a command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Function to install Maven if not installed
install_maven() {
    if ! command_exists mvn; then
        echo "Maven is not installed. Installing Maven..."
        brew install maven
    else
        echo "Maven is already installed."
    fi
}

# Function to install Node.js and npm if not installed
install_node() {
    if ! command_exists node || ! command_exists npm; then
        echo "Node.js or npm is not installed. Installing Node.js..."
        brew install node
    else
        echo "Node.js and npm are already installed."
    fi
}

# Function to install Appium if not installed
install_appium() {
    if ! command_exists appium; then
        echo "Appium is not installed. Installing Appium..."
        npm install -g appium
    else
        echo "Appium is already installed."
    fi
}

# Function to check if Xcode is installed
check_xcode() {
    if ! command_exists xcode-select; then
        echo "Xcode is not installed. Please install Xcode from the Mac App Store."
        exit 1
    else
        echo "Xcode is installed."
    fi
}

# Function to check if WebDriverAgent is installed
check_webdriveragent() {
    if [ ! -d "$WEBDRIVERAGENT_DIR" ]; then
        echo "WebDriverAgent setup is incomplete. Please install it at $WEBDRIVERAGENT_DIR."
        exit 1
    else
        echo "WebDriverAgent is correctly installed at $WEBDRIVERAGENT_DIR."
    fi
}

# Function to check if Appium server is running
check_appium_server() {
    if ! lsof -i :$APPIUM_PORT >/dev/null 2>&1; then
        echo "Appium server is not running. Starting Appium server..."
        appium &
        sleep 5 # Wait for Appium to start
    else
        echo "Appium server is already running on port $APPIUM_PORT."
    fi
}

# Function to check and boot iOS simulator
check_and_boot_ios_simulator() {
    echo "Checking for iOS simulator..."

    # Extract the device ID of iPhone 16 under iOS 18.3
    DEVICE_ID=$(xcrun simctl list devices | awk '/-- iOS 18.3 --/{flag=1; next} /--/{flag=0} flag && /iPhone 16 / {gsub(/[()]/, "", $NF); print $NF}')

    if [ -z "$DEVICE_ID" ]; then
        echo "No available iPhone 16 with iOS 18.3 found. Choose a device from the list below:"
        xcrun simctl list devices
        exit 1
    fi

    # Check if the extracted device is already booted
    BOOTED_DEVICE_ID=$(xcrun simctl list devices booted | awk '/iPhone 16 / {gsub(/[()]/, "", $NF); print $NF}')

    if [ "$BOOTED_DEVICE_ID" == "$DEVICE_ID" ]; then
        echo "iPhone 16 with iOS 18.3 is already booted (ID: $BOOTED_DEVICE_ID)."
    else
        echo "Booting iPhone 16 with iOS 18.3 (ID: $DEVICE_ID)..."
        xcrun simctl boot "$DEVICE_ID"
        sleep 5  # Wait for the simulator to boot
    fi
}

# Function to check and start Android emulator
check_and_start_android_emulator() {
    echo "Checking for Android emulator..."
    if ! adb devices | grep -q "$ANDROID_EMULATOR"; then
        echo "Android emulator $ANDROID_EMULATOR is not running. Starting emulator..."
        emulator -avd "$ANDROID_EMULATOR" &
        sleep 30 # Wait for emulator to boot
    else
        echo "Android emulator $ANDROID_EMULATOR is already running."
    fi
}

# Function to run the tests and generate the JSON report
run_tests() {
    echo "Running the tests..."
    mvn clean test -Dcucumber.plugin="json:target/cucumber-json/cucumber.json"
    if [ $? -ne 0 ]; then
        echo "Tests failed. Please check the logs."
        exit 1
    else
        echo "Tests completed successfully."
    fi
}

# Function to generate the Allure report
generate_allure_report() {
    echo "Generating Allure report..."
    allure serve "$CUCUMBER_JSON_DIR"
}

# Main script
echo "Setting up and running mobile-app-tests..."

# Step 1: Clone the repository
if [ ! -d "$REPO_DIR" ]; then
    echo "Cloning the repository..."
    git clone "$REPO_URL" "$REPO_DIR"
else
    echo "Repository already exists at $REPO_DIR."
fi

# Step 2: Navigate to the project directory
cd "$REPO_DIR" || { echo "Failed to navigate to $REPO_DIR"; exit 1; }

# Step 3: Install Maven if not installed
install_maven

# Step 4: Install Node.js, npm, and Appium
install_node
install_appium

# Step 5: Check Xcode and WebDriverAgent
check_xcode
check_webdriveragent

# Step 6: Start Appium server if not running
check_appium_server

# Step 7: Check and boot iOS simulator
check_and_boot_ios_simulator

# Step 8: Check and start Android emulator
check_and_start_android_emulator

# Step 9: Run the tests and generate the JSON report
run_tests

# Step 10: Generate Allure report
generate_allure_report

echo "Setup and test execution completed!"

