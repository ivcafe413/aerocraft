package technology.vagrant.aerocraftmod.items;

import javax.annotation.Nullable;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.ActionResultType;
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
        blockState = blockState.with(HorizontalBlock.HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
        LOGGER.debug("context placement state facing:{}", blockState.get(HorizontalBlock.HORIZONTAL_FACING));
        //Facing south, check east-west
        //Facing north, check west-east
        //Facing east, check north-south
        //Facing west, check south-north

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