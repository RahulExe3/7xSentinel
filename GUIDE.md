# 7xSentinel Guide

This guide explains the project at a high level and how to approach reviewing it safely. 7xSentinel is a rebranded fork of [The404Hacking/AndroRAT](https://github.com/The404Hacking/AndroRAT); the application code and runtime behaviour have not been rewritten as part of the rebrand.

## 1. Important safety note

This project can access sensitive Android data and device functions, including contacts, call logs, messages, location, camera, and microphone. Use it **only on a device you own or have explicit, informed permission to administer**, and only in a controlled test environment.

- Never install or run it on someone else's device without their clear consent.
- Do not use it to collect private information, intercept communications, or monitor a person without authorization.
- Do not expose a test server or listening port to the public internet.
- Use a dedicated test device or emulator with dummy data; avoid personal accounts and real messages.
- Review applicable laws, Android permissions, and the original project's licence before modifying or redistributing it.

## 2. What the project contains

The upstream project describes these main areas:

| Path | Purpose |
| --- | --- |
| `src/Androrat` | Android client source |
| `src/AndroratServer` | Java/Swing desktop server source |
| `src/api` | Supporting libraries and UI/media/map integrations |
| `src/InOut` | Shared communication/protocol code |
| `Experiment` | Experimental activity-based client |
| `doc` | Project documentation and screenshots |

Check the actual directory names in this checkout before relying on this table; the layout is inherited from the upstream project.

## 3. Before building

1. Read the root [README](README.md) and the upstream [AndroRAT repository](https://github.com/The404Hacking/AndroRAT).
2. Inspect the source, dependency declarations, build files, and licence. This is an older project, so current Android Studio, Java, Gradle, or dependency versions may not be compatible without maintenance.
3. Build only from source you have reviewed. Do not install APKs from untrusted releases or commit signing keys, passwords, tokens, or other secrets.
4. Use an isolated emulator or dedicated test device and a private test network. Populate it only with synthetic data.

No tested, version-specific build command is provided here yet; confirm the required toolchain from the repository's actual build configuration before attempting a build.

## 4. Safe test checklist

- [ ] The test device/emulator is yours or explicitly authorized.
- [ ] The person responsible for the device understands what the test does.
- [ ] The environment contains no real personal data.
- [ ] Network access is restricted to the isolated test network.
- [ ] Permissions and data flows have been reviewed.
- [ ] Any test data is deleted and the test app is removed when testing ends.

## 5. Project identity and attribution

7xSentinel is a project name used for this rebranded fork. It is not a claim that the original application has been security-audited, modernized, or made safe by the branding change. Preserve the original authors' attribution and comply with the upstream licence when making or distributing changes.

For background and upstream history, see [The404Hacking/AndroRAT](https://github.com/The404Hacking/AndroRAT).
