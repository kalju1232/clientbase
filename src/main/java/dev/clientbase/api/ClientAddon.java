package dev.clientbase.api;

import dev.clientbase.module.ModuleManager;

/**
 * Implement this in your own Fabric mod and register it in fabric.mod.json:
 *
 *   "entrypoints": { "clientbase": ["com.you.MyAddon"] }
 *
 * ClientBase will call it on startup so you can register your own modules.
 */
public interface ClientAddon {
    void onInitialize(ModuleManager modules);
}
