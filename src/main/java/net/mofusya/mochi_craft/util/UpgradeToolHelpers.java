package net.mofusya.mochi_craft.util;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.mofusya.mochi_craft.component.ModComponents;
import net.mofusya.mochi_craft.items.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class UpgradeToolHelpers {
    public static void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        int sharpen = ModComponents.SHARPEN.get(itemStack);
        component.add(Component.translatable("item.mochi_craft.mochi_tool." + (sharpen > 0 ? "sharpened" : "unsharpened")));
        component.add(Component.empty());
        component.add(Component.translatable("item.mochi_craft.mochi_tool.sharpen"));
        component.add(Component.literal("§" + (sharpen == 20 ? "e" : "7") + " " + sharpen + " / 20"));
    }

    public static boolean isFoil(ItemStack itemStack) {
        return ModComponents.SHARPEN.get(itemStack) > 0;
    }

    public static void use(Level level, Player player, InteractionHand hand, AtomicReference<InteractionResultHolder<ItemStack>> toReturn) {
        ItemHelpers.duoItemFunction(player.getMainHandItem(), player.getOffhandItem(), ((itemStack, subItemStack) -> {
            if (subItemStack.is(ModItems.MOCHI_UPGRADE_TEMPLATE.get()) && ModComponents.SHARPEN.get(itemStack) < 20) {
                if (!player.isCreative()) {
                    if (player.experienceLevel < 30) return false;
                    player.experienceLevel -= 30;
                }
                ModComponents.SHARPEN.add(itemStack);
                subItemStack.shrink(1);
                if (player.level().isClientSide) {
                    player.playSound(SoundEvents.ANVIL_USE);
                }
                toReturn.set(InteractionResultHolder.success(itemStack));
                return true;
            }
            return false;
        }));
    }

    public static void getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack, UUID attackDamageUid, UUID attackSpeedUid, ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
        if (slot == EquipmentSlot.MAINHAND) {
            int sharpen = ModComponents.SHARPEN.get(itemStack);
            if (sharpen > 20) sharpen = 20;

            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(attackDamageUid, "", sharpen * 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(attackSpeedUid, "", sharpen * 0.075, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
    }
}