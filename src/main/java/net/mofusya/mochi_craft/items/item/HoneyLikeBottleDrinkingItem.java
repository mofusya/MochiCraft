package net.mofusya.mochi_craft.items.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class HoneyLikeBottleDrinkingItem extends DrinkingItem {
    public HoneyLikeBottleDrinkingItem(Properties build, int drinkingTime) {
        super(build, drinkingTime, () -> new ItemStack(Items.GLASS_BOTTLE));
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}
