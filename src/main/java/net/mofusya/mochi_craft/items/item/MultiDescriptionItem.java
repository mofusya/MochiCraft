package net.mofusya.mochi_craft.items.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MultiDescriptionItem extends Item {
    private final int layerCount;

    public MultiDescriptionItem(Properties build, int layerCount) {
        super(build);
        this.layerCount = layerCount;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, component, flag);

        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(itemStack.getItem());
        if (itemId == null) return;

        for (int i = 0; i < this.layerCount; i++) {
            component.add(Component.translatable("item." + itemId.getNamespace() + "." + itemId.getPath() + ".desc." + i).withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
