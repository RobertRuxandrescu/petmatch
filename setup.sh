#!/bin/bash

echo "🐾 PetMatch Android App Setup 🐾"
echo "=================================="

# Check if Android Studio is installed
if ! command -v adb &> /dev/null; then
    echo "❌ Android SDK not found. Please install Android Studio and set up the SDK."
    exit 1
fi

echo "✅ Android SDK found"

# Check if local.properties exists
if [ ! -f "local.properties" ]; then
    echo "📝 Creating local.properties template..."
    cp local.properties.template local.properties
    echo "⚠️  Please update local.properties with your Android SDK path"
    echo "   Example: sdk.dir=/Users/username/Library/Android/sdk"
else
    echo "✅ local.properties found"
fi

# Check if google-services.json exists
if [ ! -f "app/google-services.json" ]; then
    echo "⚠️  google-services.json not found"
    echo "   Please create a Firebase project and download the configuration file"
    echo "   Place it in the app/ directory"
else
    echo "✅ google-services.json found"
fi

echo ""
echo "🚀 Setup complete! Next steps:"
echo "1. Update local.properties with your Android SDK path"
echo "2. Create a Firebase project and add google-services.json"
echo "3. Open the project in Android Studio"
echo "4. Sync Gradle files"
echo "5. Build and run the app"
echo ""
echo "📚 For detailed setup instructions, see README.md"



