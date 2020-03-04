package technology.vagrant.vagrantaerospace;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

import technology.vagrant.vagrantaerospace.init.ModItemGroups;

@EventBusSubscriber(modid = VagrantAerospace.MODID,
    bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
            setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "fuselage")
        );
    }

    public static <T extends IForgeRegistryEntry<T>>T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(VagrantAerospace.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>>T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}