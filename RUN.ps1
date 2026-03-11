# PowerShell Setup Script for Hibernate Department Management System

$projectPath = "C:\Users\gnana\fsds\New folder"
$mavenPath = "C:\Users\gnana\apache-maven-3.8.1\bin"

# Add Maven to PATH
if ($env:PATH -notlike "*apache-maven*") {
    $env:PATH = "$mavenPath;$env:PATH"
    Write-Host "✓ Maven path added to environment" -ForegroundColor Green
}

# Verify Maven
$mavenTest = & mvn --version 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Maven is ready" -ForegroundColor Green
} else {
    Write-Host "✗ Maven not available" -ForegroundColor Red
    exit 1
}

# Navigate to project
Set-Location $projectPath

# Main menu
do {
    Clear-Host
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "  HIBERNATE DEPARTMENT MANAGEMENT" -ForegroundColor Cyan
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "1. Build Project (mvn clean install)" -ForegroundColor Yellow
    Write-Host "2. Run Interactive Application (ClientDemo)" -ForegroundColor Yellow
    Write-Host "3. Run Test Data Loader (DataLoader)" -ForegroundColor Yellow
    Write-Host "4. Clean Build Artifacts" -ForegroundColor Yellow
    Write-Host "5. View Project Files" -ForegroundColor Yellow
    Write-Host "6. View Hibernate Configuration" -ForegroundColor Yellow
    Write-Host "0. Exit" -ForegroundColor Yellow
    Write-Host ""
    
    $choice = Read-Host "Select an option (0-6)"
    
    switch ($choice) {
        "1" {
            Write-Host ""
            Write-Host "Building project..." -ForegroundColor Cyan
            Write-Host ""
            mvn clean install -DskipTests
            Write-Host ""
            Write-Host "Build complete! Press Enter to continue..." -ForegroundColor Green
            Read-Host
        }
        
        "2" {
            Write-Host ""
            Write-Host "Starting ClientDemo application..." -ForegroundColor Cyan
            Write-Host ""
            mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.ClientDemo"
            Write-Host ""
            Write-Host "Application closed. Press Enter to continue..." -ForegroundColor Green
            Read-Host
        }
        
        "3" {
            Write-Host ""
            Write-Host "Running DataLoader with test data..." -ForegroundColor Cyan
            Write-Host ""
            mvn exec:java -Dexec.mainClass="com.klef.fsad.exam.DataLoader"
            Write-Host ""
            Write-Host "Test completed. Press Enter to continue..." -ForegroundColor Green
            Read-Host
        }
        
        "4" {
            Write-Host ""
            Write-Host "Cleaning build artifacts..." -ForegroundColor Cyan
            mvn clean
            Write-Host "Clean complete! Press Enter to continue..." -ForegroundColor Green
            Read-Host
        }
        
        "5" {
            Clear-Host
            Write-Host "PROJECT FILE STRUCTURE" -ForegroundColor Cyan
            Write-Host "======================" -ForegroundColor Cyan
            Get-ChildItem -Path $projectPath -Recurse -Include "*.java", "*.xml", "*.md", "*.bat", "*.ps1" | 
                Select-Object @{Name="Path"; Expression={$_.FullName.Replace($projectPath, '.')}} |
                Format-Table -AutoSize
            Write-Host ""
            Write-Host "Press Enter to continue..." -ForegroundColor Green
            Read-Host
        }
        
        "6" {
            Clear-Host
            Write-Host "HIBERNATE CONFIGURATION" -ForegroundColor Cyan
            Write-Host "=======================" -ForegroundColor Cyan
            Get-Content "$projectPath\src\main\resources\hibernate.cfg.xml"
            Write-Host ""
            Write-Host "Press Enter to continue..." -ForegroundColor Green
            Read-Host
        }
        
        "0" {
            Write-Host "Exiting..." -ForegroundColor Yellow
            exit 0
        }
        
        default {
            Write-Host "Invalid option. Press Enter to try again..." -ForegroundColor Red
            Read-Host
        }
    }
} while ($true)
