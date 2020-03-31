package technology.vagrant.aerocraftmod.items;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import technology.vagrant.aerocraftmod.AeroCraftMod;

public class WideBlockItem extends BlockItem {
    private static final Logger LOGGER = LogManager.getLogger(AeroCraftMod.MODID);

    public WideBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResultType tryPlace(@Nullable BlockItemUseContext context) {
        // Overriding BlockItem#tryPlace(BlockItemUseContext context)
        // to account for any generic 2-wide block

        // BlockItemUseContext blockItemUseContext =
        // this.getBlockItemUseContext(context);
        // I don't think the previous line is necessary, looking at how
        // BlockItem#getBlockItemUseContext is implemented
        // Also we're re-arranging this so that the null-check happens first
        if (context == null) {
            return ActionResultType.FAIL;
        }

        if (!context.canPlace()) {
            return ActionResultType.FAIL;
        }

        BlockState blockState = this.getStateForPlacement(context);
        if (blockState == null) {
            return ActionResultType.FAIL;
        }

        // Need to start a check if BOTH blocks can be placed
        // Since this is item placement I just have to check for adjacency
        Direction placementDirection = context.getPlacementHorizontalFacing().getOpposite();
        blockState = blockState.with(HorizontalBlock.HORIZONTAL_FACING, placementDirection);
        LOGGER.debug("context placement state facing:{}", blockState.get(HorizontalBlock.HORIZONTAL_FACING));

        BlockPos adjPos1;
        switch (placementDirection) {
            // Facing east, check north-south
            case EAST:
                adjPos1 = context.getPos().offset(Direction.NORTH);
                break;
            // Facing west, check south-north
            case WEST:
                adjPos1 = context.getPos().offset(Direction.SOUTH);
                break;
            // Facing south, check east-west
            case SOUTH:
                adjPos1 = context.getPos().offset(Direction.EAST);
                break;
            // Facing north, check west-east
            case NORTH:
            default:
                adjPos1 = context.getPos().offset(Direction.WEST);
                break;
        }
        BlockState adjBlockState1 = context.getWorld().getBlockState(adjPos1);
        if (!adjBlockState1.isReplaceable(context)) {
            return ActionResultType.FAIL;
        }

        adjBlockState1 = this.getStateForPlacement(context);
        if (!this.placeBlock(context, adjBlockState1, adjPos1)) {
            return ActionResultType.FAIL;
        }

        if (!this.placeBlock(context, blockState)) {
            // TODO: need to roll back placement of the other one if it fails
            // Can overload this#placeBlock to do all the setBlockState logic
            return ActionResultType.FAIL;
        }

        BlockPos blockpos = context.getPos();
        World world = context.getWorld();
        PlayerEntity playerentity = context.getPlayer();
        ItemStack itemstack = context.getItem();
        BlockState blockstate1 = world.getBlockState(blockpos);
        Block block = blockstate1.getBlock();

        if (block == blockState.getBlock()) {
            blockstate1 = this.func_219985_a(blockpos, world, itemstack, blockstate1);
            this.onBlockPlaced(blockpos, world, playerentity, itemstack, blockstate1);
            block.onBlockPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);
            if (playerentity instanceof ServerPlayerEntity) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerentity, blockpos, itemstack);
            }
        }

        SoundType soundtype = blockstate1.getSoundType(world, blockpos, context.getPlayer());
        world.playSound(playerentity, blockpos, this.getPlaceSound(blockstate1, world, blockpos, context.getPlayer()),
                SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        itemstack.shrink(1);
        return ActionResultType.SUCCESS;
    }

    private boolean placeBlock(BlockItemUseContext context, BlockState state, BlockPos pos) {
        return context.getWorld().setBlockState(pos, state, 11);
    }

    private BlockState func_219985_a(BlockPos p_219985_1_, World p_219985_2_, ItemStack p_219985_3_,
            BlockState p_219985_4_) {
        BlockState blockstate = p_219985_4_;
        CompoundNBT compoundnbt = p_219985_3_.getTag();
        if (compoundnbt != null) {
            CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
            StateContainer<Block, BlockState> statecontainer = p_219985_4_.getBlock().getStateContainer();

            for (String s : compoundnbt1.keySet()) {
                IProperty<?> iproperty = statecontainer.getProperty(s);
                if (iproperty != null) {
                    String s1 = compoundnbt1.get(s).getString();
                    blockstate = func_219988_a(blockstate, iproperty, s1);
                }
            }
        }

        if (blockstate != p_219985_4_) {
            p_219985_2_.setBlockState(p_219985_1_, blockstate, 2);
        }

        return blockstate;
    }

    private static <T extends Comparable<T>> BlockState func_219988_a(BlockState p_219988_0_, IProperty<T> p_219988_1_,
            String p_219988_2_) {
        return p_219988_1_.parseValue(p_219988_2_).map((p_219986_2_) -> {
            return p_219988_0_.with(p_219988_1_, p_219986_2_);
        }).orElse(p_219988_0_);
    }
}