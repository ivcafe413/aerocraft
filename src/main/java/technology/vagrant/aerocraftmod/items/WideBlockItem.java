package technology.vagrant.aerocraftmod.items;

import javax.annotation.Nullable;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import technology.vagrant.aerocraftmod.AeroCraftMod;

public class WideBlockItem extends BlockItem {
    private static final Logger LOGGER = LogManager.getLogger(AeroCraftMod.MODID);

    public WideBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResultType tryPlace(@Nullable BlockItemUseContext context) {
        //Overriding BlockItem#tryPlace(BlockItemUseContext context)
        //to account for any generic 2-wide block

        //BlockItemUseContext blockItemUseContext = this.getBlockItemUseContext(context);
        //I don't think the previous line is necessary, looking at how BlockItem#getBlockItemUseContext is implemented
        //Also we're re-arranging this so that the null-check happens first
        if(context == null) {
            return ActionResultType.FAIL;
        }

        if(!context.canPlace()) {
            //If the block at targeted blockitem use can be replaced with new block (i.e. AIR)
            return ActionResultType.FAIL;
        }

        //Should be default states
        BlockState blockState = this.getStateForPlacement(context);

        //if (!this.placeBlock(blockitemusecontext, blockstate))
        //Need to start a check if BOTH blocks can be placed
        //Since this is item placement I just have to check for adjacency
        Direction placementDirection = context.getPlacementHorizontalFacing().getOpposite();
        blockState = blockState.with(HorizontalBlock.HORIZONTAL_FACING, placementDirection);
        LOGGER.debug("context placement state facing:{}", blockState.get(HorizontalBlock.HORIZONTAL_FACING));
        
        BlockPos adjPos1;
        switch(placementDirection) {
            //Facing east, check north-south
            case EAST:
                adjPos1 = context.getPos().offset(Direction.NORTH);
                break;
            //Facing west, check south-north
            case WEST:
            adjPos1 = context.getPos().offset(Direction.SOUTH);
                break;
            //Facing south, check east-west
            case SOUTH:
            adjPos1 = context.getPos().offset(Direction.EAST);
                break;
            //Facing north, check west-east
            case NORTH:
            default:
                adjPos1 = context.getPos().offset(Direction.WEST);
                break;
        }
        BlockState adjBlockState1 = context.getWorld().getBlockState(adjPos1);
        if(adjBlockState1.isReplaceable(context)) {
            
        }

        //Let's only check left and right for a sideways placement
        //adjBlockLeft = context.getWorld().getBlockState(context.getPos().)
        
        throw new NotImplementedException("");
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        //LOGGER.log(Level.INFO, this.getTranslationKey() + ".blockState:{0}", state.toString());
        //From TallBlockItem
        //context.getWorld().setBlockState(context.getPos().up(), Blocks.AIR.getDefaultState(), 27);
        return super.placeBlock(context, state);
    }
}