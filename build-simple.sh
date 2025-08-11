#!/bin/bash

echo "🐾 PetMatch Build Script 🐾"
echo "=========================="

# Check if we have Java
if ! command -v java &> /dev/null; then
    echo "❌ Java not found. Please install Java 11 or higher."
    exit 1
fi

echo "✅ Java found: $(java -version 2>&1 | head -n 1)"

# Check if we have Android SDK
if [ -z "$ANDROID_HOME" ]; then
    echo "⚠️  ANDROID_HOME not set. Please set it to your Android SDK path."
    echo "   Example: export ANDROID_HOME=/Users/username/Library/Android/sdk"
fi

# Check project structure
echo ""
echo "📁 Checking project structure..."

if [ ! -f "app/build.gradle" ]; then
    echo "❌ app/build.gradle not found"
    exit 1
fi

if [ ! -f "build.gradle" ]; then
    echo "❌ build.gradle not found"
    exit 1
fi

if [ ! -f "settings.gradle" ]; then
    echo "❌ settings.gradle not found"
    exit 1
fi

echo "✅ Project structure looks good"

# Check for common issues
echo ""
echo "🔍 Checking for common issues..."

# Check if Hilt is properly configured
if ! grep -q "dagger.hilt.android.plugin" app/build.gradle; then
    echo "⚠️  Hilt plugin not found in app/build.gradle"
fi

# Check if Orbit MVI dependencies are present
if ! grep -q "orbit-mvi" app/build.gradle; then
    echo "⚠️  Orbit MVI dependencies not found in app/build.gradle"
fi

# Check if Compose dependencies are present
if ! grep -q "compose" app/build.gradle; then
    echo "⚠️  Compose dependencies not found in app/build.gradle"
fi

echo "✅ Basic checks completed"

echo ""
echo "🚀 To build the project, try:"
echo "1. Open in Android Studio"
echo "2. Sync Gradle files"
echo "3. Build the project"
echo ""
echo "📚 For detailed setup instructions, see README.md"



