package dev.clientbase.gui;

import dev.clientbase.ClientBase;
import dev.clientbase.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.List;

public class ClickGuiScreen extends Screen {
    private static final int BTN_W = 110, BTN_H = 20, GAP = 4, COLS = 3;

    public ClickGuiScreen() { super(Text.literal("ClientBase")); }

    private static Text label(Module m) {
        return Text.literal(m.name + ": " + (m.isEnabled() ? "\u00a7aON" : "\u00a7cOFF"));
    }

    @Override
    protected void init() {
        List<Module> modules = ClientBase.MODULES.getModules();
        int rows = (modules.size() + COLS - 1) / COLS;
        int totalW = COLS * BTN_W + (COLS - 1) * GAP;
        int startX = (width - totalW) / 2;
        int startY = (height - (rows * (BTN_H + GAP))) / 2;

        for (int i = 0; i < modules.size(); i++) {
            Module m = modules.get(i);
            int x = startX + (i % COLS) * (BTN_W + GAP);
            int y = startY + (i / COLS) * (BTN_H + GAP);
            ButtonWidget btn = ButtonWidget.builder(label(m), b -> {
                m.toggle();
                b.setMessage(label(m));
            }).dimensions(x, y, BTN_W, BTN_H).build();
            btn.setTooltip(Tooltip.of(Text.literal(m.description)));
            addDrawableChild(btn);
        }
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx);
        ctx.drawCenteredTextWithShadow(textRenderer, title, width / 2, 20, 0xFFFFFF);
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() { return false; }
}
