package technology.vagrant.aerocraftmod.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import technology.vagrant.aerocraftmod.init.ModContainerTypes;

public class WorkbenchContainer extends Container {
    public WorkbenchContainer(int windowId, PlayerInventory inventory) {
        this(windowId, inventory, null);
    }

    public WorkbenchContainer(int windowId, PlayerInventory inventory, PacketBuffer extraData) {
        super(ModContainerTypes.WORKBENCHCONTAINER, windowId);
    }

    @Override
    public boolean canInteractWith(final PlayerEntity player) {
        return true;
    }
}