package dev.clientbase.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void register(Module module) { modules.add(module); }

    public List<Module> getModules() { return Collections.unmodifiableList(modules); }

    public void tick() {
        for (Module m : modules) {
            if (!m.isEnabled()) continue;
            try {
                m.onTick();
            } catch (Throwable t) {
                t.printStackTrace();
                m.setEnabled(false); // don't crash the game over a bad module
            }
        }
    }
}
