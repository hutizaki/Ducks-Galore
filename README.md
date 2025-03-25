# Ducks Galore

A Minecraft mod that adds various rubber duck blocks to the game!

## Features

- Regular rubber ducks that quack when placed or interacted with
- Golden rubber ducks with special properties
- Gold ore rubber ducks that grant mining-related buffs
- Custom sound system following the Create mod pattern

## Requirements

- Minecraft 1.20.1
- Forge 47.2.6
- Java 17

## Building and Running

### Quick Start

Use the provided batch files for the easiest experience:

- **buildWithJava17.bat** - Builds the mod with the correct Java version
- **runWithJava17.bat** - Runs Minecraft with the mod for testing

### Manual Build

1. Ensure you have Java 17 installed
2. Set JAVA_HOME environment variable:
   ```
   set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.14.7-hotspot"
   ```
3. Run the Gradle build:
   ```
   gradlew build
   ```

### Testing In-Game

After building, you can test the mod using:
```
gradlew runClient
```

## Troubleshooting

If you encounter Java-related issues, please refer to the [Java Troubleshooting Guide](JAVA_TROUBLESHOOTING.md).

## License

All Rights Reserved

## Credits

- Created by Hutizaki
- Uses sound system inspired by the Create mod
- Thanks to the Forge community for their help and resources
