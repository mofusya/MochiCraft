package net.mofusya.mochi_craft.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.items.ModItems;

import java.util.ArrayList;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MochiCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ArrayList<RegistryObject<Item>> registries = new ArrayList<>();

        registries.addAll(ModItems.ITEMS.getMainItems());

        for (var item : registries) {
            this.simpleItem(item);
        }

        ModItems.ITEMS.getItems(1).forEach(this::handheldItem);
    }

    private void simpleItem(RegistryObject<Item> item) {
        this.withExistingParent(item.getId().getPath(),
                        new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(MochiCraft.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void handheldItem(RegistryObject<Item> item) {
        this.withExistingParent(item.getId().getPath(), new ResourceLocation("item/handheld")).texture("layer0", new ResourceLocation(MochiCraft.MOD_ID, "item/" + item.getId().getPath()));
    }
}
