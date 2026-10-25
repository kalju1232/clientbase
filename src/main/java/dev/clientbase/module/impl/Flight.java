package dev.clientbase.module.impl;

import dev.clientbase.module.Module;
import net.minecraft.entity.player.PlayerAbilities;

public class Flight extends Module {
    public Flight() { super("Flight", "Creative-style flying. Servers will usually kick for this."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        PlayerAbilities a = mc.player.getAbilities();
        a.allowFlying = true;
        a.setFlySpeed(0.1f);
    }

    @Override
    protected void onDisable() {
        if (mc.player == null) return;
        PlayerAbilities a = mc.player.getAbilities();
        if (!mc.player.isCreative() && !mc.player.isSpectator()) {
            a.allowFlying = false;
            a.flying = false;
        }
        a.setFlySpeed(0.05f);
    }
}
