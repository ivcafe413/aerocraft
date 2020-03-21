package technology.vagrant.aerocraftmod.blocks;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.IStringSerializable;

public abstract class WideBlock extends HorizontalBlock {
    public static final EnumProperty<WideSide> SIDE = EnumProperty.create("side", WideSide.class);

    public WideBlock(final Properties properties) {
        super(properties);
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