package net.mofusya.mochi_craft.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.blocks.ModBlocks;
import org.lwjgl.openal.AL;

import java.util.ArrayList;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MochiCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ArrayList<RegistryObject<Block>> registries = new ArrayList<>();

        registries.addAll(ModBlocks.BLOCKS.getMainBlocks());

        for (var block : registries){
            this.blockWithItem(block);
        }
    }

    private void blockWithItem(RegistryObject<Block> block) {
        this.simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
