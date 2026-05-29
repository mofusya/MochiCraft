package net.mofusya.mochi_craft.items.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BurningItem extends Item {
    public final int percentage;

    public BurningItem(Properties build, int percentage) {
        super(build);
        this.percentage = percentage;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (Mth.nextInt(RandomSource.create(), 1, 100) <= this.percentage && entity instanceof Player player) {
            player.setSecondsOnFire(5);
            if (!player.level().isClientSide) {
                player.displayClientMessage(Component.translatable("item.mochi_craft.choking_item.burn").withStyle(ChatFormatting.RED), true);
            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}
