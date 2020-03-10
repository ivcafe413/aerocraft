package technology.vagrant.aerocraftmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import technology.vagrant.aerocraftmod.containers.AirfoilWorkbenchContainer;

public class AirfoilWorkbenchBlock extends HorizontalBlock {
    private static final ITextComponent title = new TranslationTextComponent("container.airfoilworkbench");
    
    public AirfoilWorkbenchBlock(final Properties properties) {
        super(properties);
    }

    @Override
    // Unmapped onBlockActivated function
    public ActionResultType func_225533_a_(final BlockState state, final World world, final BlockPos pos,
            final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        if (!world.isRemote) {
            // player.addStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            NetworkHooks.openGui((ServerPlayerEntity) player, state.getContainer(world, pos), pos);
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public INamedContainerProvider getContainer(final BlockState state, World world, BlockPos pos) {
        return new SimpleNamedContainerProvider((windowId, inventory, player) -> {
            return new AirfoilWorkbenchContainer(windowId, inventory);
        }, title);
    }
}