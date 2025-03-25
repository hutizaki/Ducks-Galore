# Java Troubleshooting Guide for Ducks Galore

## Required Java Version

Ducks Galore requires **Java 17** to build and run correctly with Minecraft Forge 1.20.1.

## Common Issues

### "Extra bytes at the end of class file" Error

If you see the following error:
```
Caused by: java.lang.ClassFormatError: Extra bytes at the end of class file net/minecraft/Util
```

This is typically caused by a Java version mismatch. Make sure you're using Java 17.

### Cannot Find Java 17

If you receive errors about not finding Java 17, ensure it's installed on your system. The mod expects Java 17 to be located at:
```
C:\Program Files\Eclipse Adoptium\jdk-17.0.14.7-hotspot
```

### Fixing Java Version Issues

1. **Use the batch files provided**:
   - `runWithJava17.bat` - Runs the client with Java 17
   - `buildWithJava17.bat` - Builds the mod with Java 17

2. **Manually set JAVA_HOME**:
   ```batch
   set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.14.7-hotspot"
   set "PATH=%JAVA_HOME%\bin;%PATH%"
   ```

3. **Install Java 17** if needed:
   - Download from [Adoptium](https://adoptium.net/)
   - Select version 17 (LTS)
   - Install to the default location

## Verifying Java Version

Run this command to check your current Java version:
```
java -version
```

You should see output indicating Java 17.x.

## Need More Help?

If you continue experiencing issues with Java versions, check:
1. That you don't have conflicting Java versions in your PATH
2. That your IDE is configured to use Java 17 for this project
3. That no environment variables are overriding your Java settings

Remember that Minecraft Forge 1.20.1 specifically requires Java 17, not newer or older versions. 