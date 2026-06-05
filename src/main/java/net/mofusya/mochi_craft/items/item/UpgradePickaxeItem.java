package net.mofusya.mochi_craft.items.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
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
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.mofusya.mochi_craft.component.ModComponents;
import net.mofusya.mochi_craft.items.ModItems;
import net.mofusya.mochi_craft.util.ItemHelpers;
import net.mofusya.mochi_craft.util.UpgradeToolHelpers;
import net.mofusya.ornatelib.item.FixedPickaxeItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class UpgradePickaxeItem extends FixedPickaxeItem {

    private final UUID attackDamageUid;
    private final UUID attackSpeedUid;

    public UpgradePickaxeItem(Tier tier, int attackDamage, float attackSpeed, Properties build) {
        super(tier, attackDamage, attackSpeed, build);
        this.attackDamageUid = UUID.randomUUID();
        this.attackSpeedUid = UUID.randomUUID();
    }

    public UpgradePickaxeItem(Tier tier, int attackDamage, float attackSpeed, Properties build, boolean fixToSword) {
        super(tier, attackDamage, attackSpeed, build, fixToSword);
        this.attackDamageUid = UUID.randomUUID();
        this.attackSpeedUid = UUID.randomUUID();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        UpgradeToolHelpers.appendHoverText(itemStack, level, component, flag);
        super.appendHoverText(itemStack, level, component, flag);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        return UpgradeToolHelpers.isFoil(itemStack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        AtomicReference<InteractionResultHolder<ItemStack>> toReturn = new AtomicReference<>(super.use(level, player, hand));
        UpgradeToolHelpers.use(level, player, hand, toReturn);
        return toReturn.get();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack itemStack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getAttributeModifiers(slot, itemStack));
        UpgradeToolHelpers.getAttributeModifiers(slot, itemStack, this.attackDamageUid, this.attackSpeedUid, builder);
        return builder.build();
    }
}
