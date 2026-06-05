package net.mofusya.mochi_craft.items.item;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PolishableChokingItem extends PolishableItem{
    public final int percentage;

    public PolishableChokingItem(Properties build, @NotNull Block polishingBlock, Supplier<ItemStack> result, int percentage) {
        super(build, polishingBlock, result);
        this.percentage = percentage;
    }

    public PolishableChokingItem(Properties build, @NotNull Block polishingBlock, Supplier<ItemStack> result, @Nullable TriConsumer<Level, Player, ItemStack> func, int percentage) {
        super(build, polishingBlock, result, func);
        this.percentage = percentage;
    }

    public PolishableChokingItem(Properties build, @NotNull TagKey<Block> polishingBlockTag, Supplier<ItemStack> result, int percentage) {
        super(build, polishingBlockTag, result);
        this.percentage = percentage;
    }

    public PolishableChokingItem(Properties build, @NotNull TagKey<Block> polishingBlockTag, Supplier<ItemStack> result, @Nullable TriConsumer<Level, Player, ItemStack> func, int percentage) {
        super(build, polishingBlockTag, result, func);
        this.percentage = percentage;
    }

    public PolishableChokingItem(Properties build, @NotNull Ingredient polishingTool, Supplier<ItemStack> result, int percentage) {
        super(build, polishingTool, result);
        this.percentage = percentage;
    }

    public PolishableChokingItem(Properties build, @NotNull Ingredient polishingTool, Supplier<ItemStack> result, @Nullable TriConsumer<Level, Player, ItemStack> func, int percentage) {
        super(build, polishingTool, result, func);
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
