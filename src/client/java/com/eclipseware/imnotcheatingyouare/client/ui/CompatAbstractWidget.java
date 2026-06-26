package com.eclipseware.imnotcheatingyouare.client.ui;


import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

/**
 * Ported to MC 1.21.11: in 1.21.11 {@code AbstractWidget} exposes
 * {@link #renderWidget(net.minecraft.client.gui.GuiGraphics, int, int, float)}
 * directly — the {@code extractWidgetRenderState}/{@code GuiGraphicsExtractor}
 * split was introduced in 1.21.6+. We bridge the override back to the
 * mod's {@link GuiGraphics} wrapper so subclasses do not need to change.
 */
public abstract class CompatAbstractWidget extends AbstractWidget {
    protected CompatAbstractWidget(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
    }

    @Override
    protected void renderWidget(net.minecraft.client.gui.GuiGraphics extractor, int mouseX, int mouseY, float partialTick) {
        renderWidget(new GuiGraphics(extractor), mouseX, mouseY, partialTick);
    }

    protected abstract void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);
}
