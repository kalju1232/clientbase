package dev.clientbase.module.impl;

import dev.clientbase.module.Module;

public class Step extends Module {
    public Step() { super("Step", "Walk up full blocks without jumping."); }

    @Override
    public void onTick() {
        if (mc.player != null) mc.player.setStepHeight(1.0f);
    }

    @Override
    protected void onDisable() {
        if (mc.player != null) mc.player.setStepHeight(0.6f);
    }
}
