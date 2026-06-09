package net.mofusya.mochi_craft.items.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DrinkingItem extends Item {
    private final int drinkingTime;
    @Nullable
    private final Supplier<ItemStack> subItem;

    public DrinkingItem(Properties build, int drinkingTime) {
        this(build, drinkingTime, null);
    }

    public DrinkingItem(Properties build, int drinkingTime, @Nullable Supplier<ItemStack> subItem) {
        super(build);
        this.drinkingTime = drinkingTime;
        this.subItem = subItem;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return this.drinkingTime;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player && this.subItem != null){
            player.addItem(this.subItem.get());
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}
