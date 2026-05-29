package net.mofusya.mochi_craft.items.item;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChokingItem extends Item {
    public final int percentage;

    public ChokingItem(Properties properties, int percentage) {
        super(properties);
        this.percentage = percentage;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (Mth.nextInt(RandomSource.create(), 1, 100) <= this.percentage && entity instanceof Player player) {
            if (!player.level().isClientSide) {
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 255));
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 255));
                player.displayClientMessage(Component.translatable("item.mochi_craft.choking_item.choke"), true);
            }
        }
        return super.finishUsingItem(itemStack, level, entity);
    }
}
