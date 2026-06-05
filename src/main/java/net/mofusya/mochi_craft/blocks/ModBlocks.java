package net.mofusya.mochi_craft.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.blocks.block.QuadCropBlock;
import net.mofusya.mochi_craft.blocks.block.StickyBlock;
import net.mofusya.mochi_craft.items.ModItems;
import net.mofusya.ornatelib.registries.OrnateBlockDeferredRegister;

public class ModBlocks {
    public static final OrnateBlockDeferredRegister BLOCKS = OrnateBlockDeferredRegister.create(MochiCraft.MOD_ID, 2);

    public static final RegistryObject<Block> RICE_CROP = BLOCKS.register("rice_crop", new OrnateBlockDeferredRegister.Builder()
            .blockFunc(build -> new QuadCropBlock(build, ModItems.UNPOLISHED_RICE::get))
            .blockBuild(BlockBehaviour.Properties.of().noOcclusion().noCollission().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)), 1
    );

    public static final RegistryObject<Block> MOCHI_BLOCK = BLOCKS.register("mochi_block", new OrnateBlockDeferredRegister.Builder().blockBuildRef(Blocks.SLIME_BLOCK).blockFunc(StickyBlock::new));
    public static final RegistryObject<Block> MOCHI_BLOCK_X9 = BLOCKS.register("mochi_block_x9", new OrnateBlockDeferredRegister.Builder().blockBuildRef(Blocks.SLIME_BLOCK));
    public static final RegistryObject<Block> MOCHI_BLOCK_X81 = BLOCKS.register("mochi_block_x81", new OrnateBlockDeferredRegister.Builder().blockBuildRef(Blocks.SLIME_BLOCK));
    public static final RegistryObject<Block> MOCHI_BLOCK_X729 = BLOCKS.register("mochi_block_x729", new OrnateBlockDeferredRegister.Builder().blockBuildRef(Blocks.SLIME_BLOCK));

    public static final RegistryObject<Block> SOY_BEAN_CROP = BLOCKS.register("soy_bean_crop", new OrnateBlockDeferredRegister.Builder()
            .blockFunc(build -> new QuadCropBlock(build, ModItems.SOY_BEAN::get))
            .blockBuild((BlockBehaviour.Properties.of().noOcclusion().noCollission().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY))), 1
    );
}
