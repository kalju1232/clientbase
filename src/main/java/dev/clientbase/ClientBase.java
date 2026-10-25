package dev.clientbase;

import dev.clientbase.api.ClientAddon;
import dev.clientbase.gui.ClickGuiScreen;
import dev.clientbase.module.Module;
import dev.clientbase.module.ModuleManager;
import dev.clientbase.module.impl.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ClientBase implements ClientModInitializer {
    public static final ModuleManager MODULES = new ModuleManager();

    @Override
    public void onInitializeClient() {
        // Built-in modules
        MODULES.register(new Sprint());
        MODULES.register(new Fullbright());
        MODULES.register(new Flight());
        MODULES.register(new NoFall());
        MODULES.register(new Step());

        // Addon mods: any mod declaring a "clientbase" entrypoint gets to add modules
        for (ClientAddon addon : FabricLoader.getInstance()
                .getEntrypoints("clientbase", ClientAddon.class)) {
            try {
                addon.onInitialize(MODULES);
            } catch (Throwable t) {
                System.err.println("[ClientBase] Addon failed to load: " + addon.getClass().getName());
                t.printStackTrace();
            }
        }

        KeyBinding guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.clientbase.gui", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, "category.clientbase"));

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (guiKey.wasPressed()) mc.setScreen(new ClickGuiScreen());
            if (mc.player != null && mc.world != null) MODULES.tick();
        });

        // Simple HUD list of enabled modules
        HudRenderCallback.EVENT.register((ctx, tickDelta) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.options.hudHidden) return;
            int y = 4;
            for (Module m : MODULES.getModules()) {
                if (!m.isEnabled()) continue;
                ctx.drawTextWithShadow(mc.textRenderer, m.name, 4, y, 0x55FF55);
                y += 10;
            }
        });
    }
}
