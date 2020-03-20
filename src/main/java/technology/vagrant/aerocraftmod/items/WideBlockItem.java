package technology.vagrant.aerocraftmod.items;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
    public ActionResultType tryPlace(BlockItemUseContext context) {
        return super.tryPlace(context);
    }

    @Override
    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        //LOGGER.log(Level.INFO, this.getTranslationKey() + ".blockState:{0}", state.toString());
        //From TallBlockItem
        //context.getWorld().setBlockState(context.getPos().up(), Blocks.AIR.getDefaultState(), 27);
        return super.placeBlock(context, state);
    }
}