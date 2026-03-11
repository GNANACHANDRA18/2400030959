@echo off
REM Setup and Run Hibernate Department Management System
REM This script handles Maven setup and project execution

setlocal enabledelayedexpansion

REM Colors for output
cls
echo.
echo ========================================
echo  HIBERNATE DEPARTMENT MANAGEMENT SYSTEM
echo ========================================
echo.

REM Check if Maven is installed
echo Checking Maven installation...
mvn --version >nul 2>&1
if errorlevel 1 (
    echo Maven not found in PATH. Setting up Maven...
    set "MAVEN_PATH=C:\Users\gnana\apache-maven-3.8.1\bin"
    set "PATH=!MAVEN_PATH!;!PATH!"
    echo Maven path added to environment
) else (
    echo Maven found!
)

REM Change to project directory
cd /d "C:\Users\gnana\fsds\New folder"

echo.
echo ========================================
echo  BUILD MENU
echo ========================================
echo 1. Build Project (mvn clean install)
echo 2. Run Interactive Application (ClientDemo)
echo 3. Run Test Data Loader (DataLoader)
echo 4. Clean Build Artifacts
echo 5. View Project Structure
echo 0. Exit
echo.

set /p choice="Select an option (0-5): "

if "%choice%"=="1" (
    echo.
    echo Building project...
    mvn clean install -DskipTests
    pause
) else if "%choice%"=="2" (
    echo.
    echo Starting ClientDemo application...
    echo.
    mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
    pause
) else if "%choice%"=="3" (
    echo.
    echo Running DataLoader with test data...
    echo.
    mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.DataLoader"
    pause
) else if "%choice%"=="4" (
    echo.
    echo Cleaning build artifacts...
    mvn clean
    echo Clean complete!
    pause
) else if "%choice%"=="5" (
    echo.
    echo Project Structure:
    echo.
    tree /F /A
    pause
) else if "%choice%"=="0" (
    echo Exiting...
    exit /b 0
) else (
    echo Invalid option. Please try again.
    pause
    cls
    goto menu
)

endlocal
pause
