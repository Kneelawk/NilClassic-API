# NilClassic-API

A simple API for modding classic using NilLoader.

## A quick note

NilLoader is not a general purpose loader intended to replace Fabric or Forge.
If you're here, you should either have a specific use case (e.g. wanting to
patch mods on old versions with broken tooling) or just want to mess around with
a new toy for the sake of it.

Additionally, NilLoader is currently *experimental* and ***API/ABI stability is
not yet guaranteed***.

If you're okay with that, then cool. Let's continue.

## The API

### Entrypoints

NilClassic-API provides entrypoints:

* `client-init` - Earliest point when it is safe to access Minecraft code. This can be used for registering event
  listeners.

## Depending on NilClassic-API

NilClassic can be used by depending on it in your gradle script:
```groovy
repositories {
    maven {
        name = 'Kneelawk'
        url = 'https://kneelawk.com/maven/'
    }
}

dependencies {
    // if you are working on a client-side mod:
    implementation "com.kneelawk.nilclassic-api:client:${nilclassicApiVersion}"

    // if you are working on a server-side mod:
    implementation "com.kneelawk.nilclassic-api:server:${nilclassicApiVersion}"

    // if you are working on common code shared between client-side and server-side:
    implementation "com.kneelawk.nilclassic-api:common:${nilclassicApiVersion}"
}
```

## Building

The current version of NilGradle gets confused by classic mappings causing the mod to need to be built in 3 stages via
separate gradle invocations. These can be run via a bash command:

```bash
./gradlew clean && ./gradlew check && ./gradlew build
```

## Decompiling

Decompiling is broken on classic versions. Something about the class files just break VineFlower.

## How do I launch a development environment?

NilGradle does not currently (and likely never will) offer a dev environment. In
the future, NilLoader may gain the ability to be run a normal game in dev mode
and then be attached to an IDE for code hotswap. **You will have to restart the
game every time you make changes to your mod**.
