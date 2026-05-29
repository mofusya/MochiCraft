package net.mofusya.mochi_craft.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.blocks.block.QuadCropBlock;
import net.mofusya.mochi_craft.items.ModItems;
import net.mofusya.ornatelib.registries.OrnateBlockDeferredRegister;

public class ModBlocks {
    public static final OrnateBlockDeferredRegister BLOCKS = OrnateBlockDeferredRegister.create(MochiCraft.MOD_ID);

    public static final RegistryObject<Block> RICE_CROP = BLOCKS.register("rice_crop", new OrnateBlockDeferredRegister.Builder()
            .blockFunc(build -> new QuadCropBlock(build, ModItems.UNPOLISHED_RICE::get))
            .blockBuild(BlockBehaviour.Properties.of().noOcclusion().noCollission().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY))
    );
}
