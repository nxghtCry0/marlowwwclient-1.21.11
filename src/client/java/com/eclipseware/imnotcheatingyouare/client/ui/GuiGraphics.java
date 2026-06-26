package com.eclipseware.imnotcheatingyouare.client.ui;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Wrapper around Minecraft's {@link net.minecraft.client.gui.GuiGraphics} so the
 * rest of the mod can keep using the same helper method names that were originally
 * introduced when the mod targeted MC 26.1.x (where the underlying class was
 * called {@code GuiGraphicsExtractor}).
 *
 * Ported to MC 1.21.11: GuiGraphicsExtractor no longer exists, the standard
 * {@code net.minecraft.client.gui.GuiGraphics} is used instead.
 */
public class GuiGraphics {
    private final net.minecraft.client.gui.GuiGraphics extractor;

    public GuiGraphics(net.minecraft.client.gui.GuiGraphics extractor) {
        this.extractor = extractor;
    }

    public net.minecraft.client.gui.GuiGraphics extractor() {
        return this.extractor;
    }

    public void fill(int minX, int minY, int maxX, int maxY, int color) {
        this.extractor.fill(minX, minY, maxX, maxY, color);
    }

    public void renderOutline(int x, int y, int w, int h, int color) {
        this.extractor.fill(x, y, x + w, y + 1, color);
        this.extractor.fill(x, y + h - 1, x + w, y + h, color);
        this.extractor.fill(x, y, x + 1, y + h, color);
        this.extractor.fill(x + w - 1, y, x + w, y + h, color);
    }

    public void drawCenteredString(Font font, Component text, int x, int y, int color) {
        this.extractor.drawCenteredString(font, text, x, y, color);
    }

    public void drawString(Font font, Component text, int x, int y, int color, boolean dropShadow) {
        this.extractor.drawString(font, text, x, y, color, dropShadow);
    }

    public void drawWordWrap(Font font, Component text, int x, int y, int width, int color) {
        this.extractor.drawWordWrap(font, text, x, y, width, color);
    }

    public void blit(Identifier id, int x, int y, int u, int v, int width, int height) {
    }

    public void blitSprite(Identifier id, int x, int y, int width, int height) {
    }
}
