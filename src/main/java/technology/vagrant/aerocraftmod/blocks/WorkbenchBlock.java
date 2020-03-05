package technology.vagrant.aerocraftmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import technology.vagrant.aerocraftmod.init.ModEntityTypes;

public class WorkbenchBlock extends HorizontalBlock {
    public WorkbenchBlock(final Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return ModEntityTypes.WORKBENCHTILE.create();
    }
}