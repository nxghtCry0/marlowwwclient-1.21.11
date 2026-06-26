 package com.eclipseware.imnotcheatingyouare.client.ui;
 import net.minecraft.client.gui.components.AbstractSliderButton;
 import net.minecraft.network.chat.Component;

 /**
  * Ported to MC 1.21.11: in 1.21.11 {@code AbstractSliderButton} exposes
  * {@link #renderWidget(net.minecraft.client.gui.GuiGraphics, int, int, float)}
  * directly — the {@code extractWidgetRenderState}/{@code GuiGraphicsExtractor}
  * split was introduced in 1.21.6+. We bridge the override back to the
  * mod's {@link GuiGraphics} wrapper so subclasses do not need to change.
  */
 public abstract class CompatSliderButton extends AbstractSliderButton {
   protected CompatSliderButton(int x, int y, int width, int height, Component message, double value) {
     super(x, y, width, height, message, value);
   }
   @Override
   public void renderWidget(net.minecraft.client.gui.GuiGraphics extractor, int mouseX, int mouseY, float partialTick) {
     super.renderWidget(extractor, mouseX, mouseY, partialTick);
     renderWidget(new GuiGraphics(extractor), mouseX, mouseY, partialTick);
   }
   protected abstract void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);
 }
