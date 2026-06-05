package net.mofusya.mochi_craft.data.loottable;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.blocks.ModBlocks;

import java.util.ArrayList;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        ArrayList<RegistryObject<Block>> registries = new ArrayList<>();
        registries.addAll(ModBlocks.BLOCKS.getMainBlocks());

        for (var block : registries){
            this.dropSelf(block.get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        ArrayList<RegistryObject<Block>> blocks = new ArrayList<>();
        blocks.addAll(ModBlocks.BLOCKS.getMainBlocks());
        return blocks.stream().map(RegistryObject::get).toList();
    }
}
