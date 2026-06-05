package net.mofusya.mochi_craft.blocks.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class StickyBlock extends Block {
    public StickyBlock(Properties build) {
        super(build);
    }

    @Override
    public boolean canStickTo(BlockState state, BlockState other) {
        if (other.is(Blocks.SLIME_BLOCK) || other.is(Blocks.HONEY_BLOCK)) return false;

        return super.canStickTo(state, other);
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return true;
    }
}
