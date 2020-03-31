package technology.vagrant.aerocraftmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import technology.vagrant.aerocraftmod.gui.AirfoilWorkbenchContainerScreen;
import technology.vagrant.aerocraftmod.init.ModContainerTypes;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = AeroCraftMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEventSubscriber {
    private static final Logger LOGGER = LogManager.getLogger(AeroCraftMod.MODID);
    
    @SubscribeEvent
    public static void onRegisterClientEvents(final FMLClientSetupEvent event) {
        //Gui registration
        //Adding DeferredWorkQueue for thread safety?
        DeferredWorkQueue.runLater(() -> {
            ScreenManager.registerFactory(ModContainerTypes.AIRFOILWORKBENCHCONTAINER, AirfoilWorkbenchContainerScreen::new);
            LOGGER.debug("Registering ContainerType ContainerScreen");
        });
    }
}