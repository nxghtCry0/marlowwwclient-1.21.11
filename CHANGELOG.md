# Marlow Client v3.1 Changelog

This is a minor release (v3.1) adding the new WebStun module and improving the LungeAssist module.

## 🚀 New Features

### **WebStun**
- **Automatic Cobweb Placement:** Places a cobweb directly under target player's feet when their shield is disabled by AutoShieldBreaker or when hit.
- **Anti-Cheat Safe:** Spans actions across multiple ticks using a multi-tick state machine.
- **Silent Rotations & Placements:** Uses exact GCD-snapped rotations and post-movement callbacks to place blocks against solid support blocks, preventing GrimAC alerts.

## 🛠️ Bug Fixes & Improvements

### **LungeAssist**
- **Jumping Apex Logic:** Fixed LungeAssist so that if enabled while jumping/falling, it waits for the apex of the jump before initiating the spear lunge attack.
- **GPL-3 Compliance:** Updated base implementation to align with project licensing constraints.

### **Bypass Infrastructure**
- **MultiPlayerGameMode client-carried sync:** Prevented duplicate selection packets (BadPacketsA bypass) by reflectively syncing carried index fields.
- **Mixin Optimization:** Replaced static mixin fields with localized manager references to prevent loader crashes.

---

## v3.1.2-1.21.11 — Backport to Minecraft 1.21.11

This is a backport of the v3.1.2 codebase to **Minecraft Java Edition 1.21.11**
(the last 1.21.x release before Mojang's year-based 26.x rename). It uses
**Mojang official mappings** and the **Fabric Loader**.

> **Deprecation note:** The mod's primary target is MC 26.1.2+. This 1.21.11
> branch is maintained as a backport and may lag behind the mainline by one or
> more releases.

### Build configuration
- `minecraft_version` → `1.21.11`
- `loader_version` → `0.19.3`
- `fabric_version` → `0.141.4+1.21.11`
- Loom plugin switched from `net.fabricmc.fabric-loom` (LoomNoRemap, for
  unobfuscated MC 26.x) to plain `fabric-loom` (LoomGradlePlugin, for
  obfuscated MC 1.21.x).
- Loom version bumped to `1.17.12` and Gradle wrapper to `9.5.0`.
- Mojang mappings supplied via `loom.officialMojangMappings()` — 1.21.11
  still ships obfuscated jars with `client_mappings`/`server_mappings`.

### API surface changes (26.1.x → 1.21.11)
- `net.minecraft.client.gui.GuiGraphicsExtractor` (1.21.6+ split-render API)
  was removed; all usages now route through the standard
  `net.minecraft.client.gui.GuiGraphics`. The mod's `GuiGraphics` wrapper
  now delegates to MC's `GuiGraphics` directly.
- `CompatAbstractWidget`, `CompatSliderButton`, `CompatScreen`: bridged from
  the 1.21.6+ `extractWidgetRenderState` / `extractRenderState` overrides back
  to the 1.21.11 `renderWidget` / `render` overrides.
- `GuiMixin`: target changed from `Gui.extractRenderState` (1.21.6+) to
  `Gui.render(GuiGraphics, DeltaTracker)`.
- `GameRendererMixin#bobHurt`: signature changed from
  `(CameraRenderState, PoseStack)` to the 1.21.11 `(PoseStack, float)`.
- `LightTextureMixin`: `net.minecraft.client.renderer.Lightmap` →
  `net.minecraft.client.renderer.LightTexture`.
- Screen subclasses: `extractRenderState` → `render`,
  `extractBackground` → `renderBackground`.
- `MultiPlayerGameMode.handleContainerInput` → `handleInventoryMouseClick`.
- `net.minecraft.world.inventory.ContainerInput` → `ClickType`
  (same enum constants: `PICKUP`, `QUICK_MOVE`, `SWAP`, …).
- `ServerboundInteractPacket` constructor → static factory
  `ServerboundInteractPacket.createInteractionPacket(entity, shift, hand, pos)`.
- `Player.sendSystemMessage(Component)` → `Player.displayClientMessage(Component, false)`.
- `ClientboundSetEntityMotionPacket.id()` / `.movement()` →
  `getId()` / `getMovement()`.
- `PlainTextContents.drawString()` → `PlainTextContents.text()`.
- `GuiGraphics.item(ItemStack, int, int)` → `renderItem(...)`.
- `GuiGraphicsExtractor.text(...)` / `.centeredText(...)` →
  `GuiGraphics.drawString(...)` / `.drawCenteredString(...)`.
- `Camera.getViewRotationProjectionMatrix(Matrix4f)` removed; `RenderUtils`
  now computes the combined matrix manually from `camera.rotation()` and
  `GameRenderer.getProjectionMatrix(float)`.
- `ChunkPos.x()` / `.z()` (methods) → `ChunkPos.x` / `.z` (public fields).
- Fabric API: `ClientCommands` → `ClientCommandManager`.

### Mixin configuration
- Removed `refmap` field from both mixin JSONs — Loom 1.17+ no longer
  generates a refmap by default, and Fabric Loader 0.19+ resolves
  Mojang-named mixin targets at runtime natively.

### Cleanup
- Removed stray `bin/`, root-level `ImnotcheatingyouareClient.java`, and
  root-level `net/minecraft/...` tree from the original repo (not part of
  the Gradle source sets).
