package technology.vagrant.aerocraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;
import technology.vagrant.aerocraftmod.blocks.*;
import technology.vagrant.aerocraftmod.init.ModBlocks;
import technology.vagrant.aerocraftmod.init.ModItemGroups;
import technology.vagrant.aerocraftmod.tileentities.WorkbenchTileEntity;

@EventBusSubscriber(modid = AeroCraftMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry()
                .registerAll(setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "fuselage"));
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)),
                        "aluminum_ore"),
                setup(new WorkbenchBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F)),
                        "workbenchblock"));
    }

    @SubscribeEvent
    public static void onRegisterTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry()
                .registerAll(setup(
                        TileEntityType.Builder.create(WorkbenchTileEntity::new, ModBlocks.WORKBENCHBLOCK).build(null),
                        "workbenchtile"));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(AeroCraftMod.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}