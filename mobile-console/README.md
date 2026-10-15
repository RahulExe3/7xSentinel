# AndroConsole for Android

A Java-based, mobile-first adaptation of the legacy AndroRAT desktop console's section structure.

## Current scope

- Native Android Views, no Kotlin app source and no third-party UI libraries.
- Mobile navigation for the legacy console's overview, client/session status, event log, preferences, and feature-panel categories.
- Preview-only module panels; no device discovery, remote command execution, surveillance, or device data collection is implemented.
- The legacy desktop GUI remains in `src/AndroratServer/`; its Swing components are not directly reusable on Android, so the interface is adapted rather than pixel-identical.

## Build an APK

Open the `mobile-console` directory in Android Studio, or build it with Gradle 8.9 and JDK 17:

```sh
gradle assembleDebug
```

The debug APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

Application label: `AndroConsole`  
Package name: `com.androconsole`
