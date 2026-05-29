package net.mofusya.mochi_craft.blocks.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class QuadCropBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;

    private static final VoxelShape[] SHAPES_BY_AGE = new VoxelShape[]{
            box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0)
    };

    private final Supplier<ItemLike> seed;

    public QuadCropBlock(Properties build, Supplier<ItemLike> seed) {
        super(build);
        this.seed = seed;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.seed.get();
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPES_BY_AGE[this.getAge(state)];
    }
}
