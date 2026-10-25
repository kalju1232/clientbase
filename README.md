# ClientBase (Fabric 1.20.1)

## Build
Requires JDK 17.
    gradle wrapper --gradle-version 8.5     # once, if no wrapper present
    ./gradlew build
Jar ends up in build/libs/clientbase-1.0.0.jar

## Install
Install Fabric Loader for 1.20.1, then drop the jar + Fabric API into .minecraft/mods.
Any other Fabric mods (Sodium, Iris, etc.) go in the same folder and work normally.

## Use
RIGHT SHIFT opens the module GUI. Enabled modules show on the HUD.

## Writing an addon (your own modules, as a separate mod)
1. Add the clientbase jar as a dependency of your mod.
2. In your fabric.mod.json:
       "entrypoints": { "clientbase": ["com.you.MyAddon"] }
3. Implement dev.clientbase.api.ClientAddon:
       public class MyAddon implements ClientAddon {
           public void onInitialize(ModuleManager m) { m.register(new MyModule()); }
       }
4. Extend dev.clientbase.module.Module and override onTick/onEnable/onDisable.

## No-install build (GitHub)
1. Create a free GitHub repo and upload all of these files (keep the .github folder).
2. Open the Actions tab, wait for the "build" run to finish (~3 min).
3. Click the run, download the "clientbase-jar" artifact, unzip it, and put the jar in your mods folder.
