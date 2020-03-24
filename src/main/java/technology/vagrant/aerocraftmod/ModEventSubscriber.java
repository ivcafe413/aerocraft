package technology.vagrant.aerocraftmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;
import technology.vagrant.aerocraftmod.blocks.AirfoilWorkbenchBlock;
import technology.vagrant.aerocraftmod.containers.AirfoilWorkbenchContainer;
import technology.vagrant.aerocraftmod.init.ModBlocks;
import technology.vagrant.aerocraftmod.init.ModItemGroups;
import technology.vagrant.aerocraftmod.items.WideBlockItem;

@EventBusSubscriber(modid = AeroCraftMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                // setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)),
                //         "aluminum_ore"),
                setup(new AirfoilWorkbenchBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2.5F)),
                        "airfoilworkbenchblock"));
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry()
                .registerAll(
                    //setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "fuselage"),
                    setup(new WideBlockItem(ModBlocks.AIRFOILWORKBENCHBLOCK, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "airfoilworkbench"));
    }

    @SubscribeEvent
    public static void onRegisterTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        // event.getRegistry()
        // .registerAll(setup(
        // TileEntityType.Builder.create(WorkbenchTileEntity::new,
        // ModBlocks.WORKBENCHBLOCK).build(null),
        // "workbenchtile"));
    }

    @SubscribeEvent
    public static void onRegisterContainerTypes(RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().registerAll(
                // setup(IForgeContainerType.create(WorkbenchContainer::new),
                // "workbenchcontainer")
                // I guess as a result of changes to the IFactory something?
                // setup(IForgeContainerType.create((windowIn, inventory, data) -> {
                // return new WorkbenchContainer(windowIn, inventory);
                // Noooo, WorkbenchContainer is vanilla, and I was using that. Bad name
                setup(IForgeContainerType.create(AirfoilWorkbenchContainer::new), "airfoilworkbenchcontainer"));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(AeroCraftMod.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}