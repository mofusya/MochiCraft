package net.mofusya.mochi_craft.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.util.ItemHelpers;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MochiCraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.mochi_craft.main"))
                    .displayItems((parameters, output) -> {
                        output.acceptAll(ItemHelpers.itemRegistries2ItemStacks(ModItems.ITEMS.getMainItems()));
                    })
                    .build());
}
