package technology.vagrant.aerocraftmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;

public abstract class WideBlock extends HorizontalBlock {
    public static final EnumProperty<WideSide> SIDE = EnumProperty.create("side", WideSide.class);

    public WideBlock(final Block.Properties properties) {
        super(properties);
    }

    @Override
	protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(SIDE);
	}

    public enum WideSide implements IStringSerializable {
        LEFT("left"), RIGHT("right");

        private final String name;

        private WideSide(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

}