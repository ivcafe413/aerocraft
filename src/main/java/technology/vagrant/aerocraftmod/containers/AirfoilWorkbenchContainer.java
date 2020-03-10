package technology.vagrant.aerocraftmod.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import technology.vagrant.aerocraftmod.init.ModContainerTypes;

public class AirfoilWorkbenchContainer extends Container {
    public CraftingInventory craftMatrix;
    public CraftResultInventory craftResult;

    public AirfoilWorkbenchContainer(int windowId, PlayerInventory inventory) {
        this(windowId, inventory, null);
    }

    public AirfoilWorkbenchContainer(int windowId, PlayerInventory inventory, PacketBuffer extraData) {
        super(ModContainerTypes.AIRFOILWORKBENCHCONTAINER, windowId);
        this.craftMatrix = new CraftingInventory(this, 4, 3);
        this.craftResult = new CraftResultInventory();

        //4x3 Craft Matrix
        for (int row = 0; row < 3; row++) {
            for(int col = 0; col < 4; col++) {
                this.addSlot(new Slot(this.craftMatrix, col + (row * 4), 27 + (col * 18), 6 + (row * 18)));
            }
        }

        //Result Slot
        this.addSlot(new CraftingResultSlot(inventory.player, this.craftMatrix, this.craftResult, 0, 117, 24));

        //Player Inventory
        //Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 9 + (col * 18), 128));
        }
        //Main Inventory
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory, col + (row * 9) + 9, 9 + (col * 18), 70 + (row * 18)));
            }
        }
    }

    @Override
    public boolean canInteractWith(final PlayerEntity player) {
        return true;
    }
}