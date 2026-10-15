# 7xConsole for Android

A standalone, mobile-first companion dashboard prototype for the 7xSentinel authorised lab project.

## Current scope
- Native Android activity with a compact dark interface and 7x branding.
- Local demo/status cards for workspace, connection status, activity log, and configuration.
- No device discovery, remote command execution, surveillance, or data collection is implemented.

## Build an APK
Open the `mobile-console` directory in Android Studio, or build it with Gradle 8.9 and JDK 17:

```sh
gradle assembleDebug
```

The debug APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

Package name: `com.sevenx.console`
