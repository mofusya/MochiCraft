package net.mofusya.mochi_craft.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.blocks.ModBlocks;
import net.mofusya.mochi_craft.component.ModComponents;
import net.mofusya.mochi_craft.util.ItemHelpers;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MochiCraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.mochi_craft.main"))
                    .icon(() -> new ItemStack(ModItems.CUT_MOCHI.get()))
                    .displayItems((parameters, output) -> {
                        output.acceptAll(ItemHelpers.itemRegistries2ItemStacks(ModItems.ITEMS.getMainItems()));
                        output.acceptAll(ItemHelpers.blockRegistries2ItemStacks(ModBlocks.BLOCKS.getMainBlocks()));
                        output.acceptAll(ItemHelpers.itemRegistries2ItemStacks(ModItems.ITEMS.getItems(1)));
                        output.acceptAll(ItemHelpers.itemRegistries2ItemStacks(ModItems.ITEMS.getItems(1)).stream().map(itemStack -> {
                            ModComponents.SHARPEN.set(itemStack, 20);
                            return itemStack;
                        }).toList());
                    })
                    .build());
}
