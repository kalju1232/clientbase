package dev.clientbase.module.impl;

import dev.clientbase.module.Module;

public class Sprint extends Module {
    public Sprint() { super("Sprint", "Always sprint while moving forward."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.input.movementForward > 0 && !mc.player.isSneaking()
                && !mc.player.horizontalCollision) {
            mc.player.setSprinting(true);
        }
    }
}
