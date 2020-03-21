package technology.vagrant.aerocraftmod.items;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import org.apache.commons.lang3.NotImplementedException;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.ActionResultType;
import technology.vagrant.aerocraftmod.AeroCraftMod;

public class WideBlockItem extends BlockItem {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger(AeroCraftMod.MODID);

    public WideBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResultType tryPlace(@Nullable BlockItemUseContext context) {
        //Overriding BlockItem#tryPlace(BlockItemUseContext context)
        //to account for any generic 2-wide block
        //return super.tryPlace(context);

        //Reducing if-else nesting

        //BlockItemUseContext blockItemUseContext = this.getBlockItemUseContext(context);
        //I don't think the previous line is necessary, looking at how BlockItem#getBlockItemUseContext is implemented
        //Also we're re-arranging this so that the null-check happens first
        if(context == null) {
            return ActionResultType.FAIL;
        }

        if(!context.canPlace()) {
            //If the block at targeted blockitem use can be replaced with new block
            //(ex: AIR)
            return ActionResultType.FAIL;
        }

        BlockState blockState = this.getStateForPlacement(context);
        if(blockState == null) {
            return ActionResultType.FAIL;
        }

        //Divergent from BlockItem#tryPlace
        //if (!this.placeBlock(blockitemusecontext, blockstate))
        //Need to start a check if BOTH blocks can be place
        //Also, this could be the SECOND block (i.e. right-side)
        //This will require split logic for each scenario

        //Can I store the left-side/right-side problem with BlockState?
        
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