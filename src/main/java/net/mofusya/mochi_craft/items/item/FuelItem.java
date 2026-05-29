package net.mofusya.mochi_craft.items.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class FuelItem extends Item {
    public final int burnCount;

    public FuelItem(Properties build, int burnCount) {
        super(build);
        this.burnCount = burnCount;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 200 * this.burnCount;
    }
}
