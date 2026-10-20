@echo off
setlocal enabledelayedexpansion
cd /d "%~dp0"

echo ============================================
echo  Tengwar translator - debug APK build
echo ============================================
echo.

where java >nul 2>nul
if errorlevel 1 (
    echo [ERROR] Java not found in PATH.
    echo         Install a JDK 17 and make sure "java" is available.
    exit /b 1
)

if not defined ANDROID_HOME (
    if defined ANDROID_SDK_ROOT (
        set "ANDROID_HOME=%ANDROID_SDK_ROOT%"
    ) else (
        echo [ERROR] ANDROID_HOME is not set.
        echo         Point it to your Android SDK, e.g.:
        echo         set ANDROID_HOME=%%LOCALAPPDATA%%\Android\Sdk
        exit /b 1
    )
)

echo Using JAVA_HOME      : %JAVA_HOME%
echo Using ANDROID_HOME   : %ANDROID_HOME%
echo.

echo Building debug APK...
echo.
call gradlew.bat assembleDebug
if errorlevel 1 (
    echo.
    echo [ERROR] Build failed. See the Gradle output above.
    exit /b 1
)

set "APK=app\build\outputs\apk\debug\app-debug.apk"
echo.
if exist "%APK%" (
    echo [OK] APK built successfully:
    echo      %CD%\%APK%
) else (
    echo [ERROR] Build reported success but APK was not found at %APK%.
    exit /b 1
)

endlocal
