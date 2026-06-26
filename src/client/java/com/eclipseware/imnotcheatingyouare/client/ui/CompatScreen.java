package com.eclipseware.imnotcheatingyouare.client.ui;


import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Ported to MC 1.21.11: in 1.21.11 {@code Screen} exposes
 * {@link #render(net.minecraft.client.gui.GuiGraphics, int, int, float)}
 * directly — the {@code render}/{@code GuiGraphicsExtractor}
 * split was introduced in 1.21.6+. We bridge the override back to the
 * mod's {@link GuiGraphics} wrapper so subclasses do not need to change.
 */
public abstract class CompatScreen extends Screen {
    protected CompatScreen(Component title) {
        super(title);
    }

    @Override
    public void render(net.minecraft.client.gui.GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        render(new GuiGraphics(guiGraphics), mouseX, mouseY, partialTick);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (GuiEventListener child : children()) {
            if (child instanceof Renderable) {
                Renderable renderable = (Renderable) child;
                renderable.render(guiGraphics.extractor(), mouseX, mouseY, partialTick);
            }
        }
    }
}
