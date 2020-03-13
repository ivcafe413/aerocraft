package technology.vagrant.aerocraftmod.gui;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import technology.vagrant.aerocraftmod.containers.AirfoilWorkbenchContainer;

public class AirfoilWorkbenchContainerScreen extends ContainerScreen<AirfoilWorkbenchContainer> {
    private static final ResourceLocation AIRFOILWORKBENCH_GUI_TEXTURE = new ResourceLocation(
            "aerocraftmod:textures/gui/airfoilworkbench.png");

    public AirfoilWorkbenchContainerScreen(AirfoilWorkbenchContainer screenContainer, PlayerInventory inv,
            ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        getMinecraft().getTextureManager().bindTexture(AIRFOILWORKBENCH_GUI_TEXTURE);
        int x = this.guiLeft;
        int y = this.guiTop;

        // Screen#blit(x, y, u, v, w, h)
        // Assumes size of 176/166 (inherited from ContainerScreen) if not given
        this.blit(x, y, 0, 0, this.xSize, this.ySize);

        // No tile entity
    }

    @Override
    protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        // Copied from AbstractFurnaceScreen#drawGuiContainerForegroundLayer
        //String s = this.title.getFormattedText();
        //this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 0x404040);
    }

    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}