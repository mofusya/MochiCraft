package net.mofusya.mochi_craft.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.List;

public class ItemHelpers {
    public static void duoItemFunction(ItemStack itemStack, ItemStack subItemStack, DuoItemPredicate func) {
        if (!func.run(itemStack, subItemStack)) {
            func.run(subItemStack, itemStack);
        }
    }

    public static List<ItemStack> itemRegistries2ItemStacks(List<RegistryObject<Item>> items) {
        return items.stream().map(RegistryObject::get).map(ItemStack::new).toList();
    }

    @FunctionalInterface
    public interface DuoItemPredicate {
        boolean run(ItemStack itemStack, ItemStack subItemStack);
    }
}
