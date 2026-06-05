package net.mofusya.mochi_craft.items.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.mofusya.mochi_craft.util.ItemHelpers;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class ItemNameBlockPolishableItem extends ItemNameBlockItem {
    @Nullable
    private final Ingredient polishingTool;
    @Nullable
    private final Block polishingBlock;
    @Nullable
    private final TagKey<Block> polishingBlockTag;
    private final Supplier<ItemStack> result;
    @Nullable
    private final TriConsumer<Level, Player, ItemStack> func;

    public ItemNameBlockPolishableItem(Block crop, Properties build, @NotNull Ingredient polishingTool, Supplier<ItemStack> result) {
        this(crop, build, polishingTool, result, null);
    }

    public ItemNameBlockPolishableItem(Block crop, Properties build, @NotNull Block polishingBlock, Supplier<ItemStack> result) {
        this(crop, build, polishingBlock, result, null);
    }

    public ItemNameBlockPolishableItem(Block crop, Properties build, @NotNull TagKey<Block> polishingBlockTag, Supplier<ItemStack> result) {
        this(crop, build, polishingBlockTag, result, null);
    }

    public ItemNameBlockPolishableItem(Block crop, Properties build, @NotNull Ingredient polishingTool, Supplier<ItemStack> result, @Nullable TriConsumer<Level, Player, ItemStack> func) {
        super(crop, build);
        this.polishingTool = polishingTool;
        this.polishingBlock = null;
        this.polishingBlockTag = null;
        this.result = result;
        this.func = func;
    }

    public ItemNameBlockPolishableItem(Block crop, Properties build, @NotNull Block polishingBlock, Supplier<ItemStack> result, @Nullable TriConsumer<Level, Player, ItemStack> func) {
        super(crop, build);
        this.polishingBlock = polishingBlock;
        this.polishingTool = null;
        this.polishingBlockTag = null;
        this.result = result;
        this.func = func;
    }

    public ItemNameBlockPolishableItem(Block crop, Properties build, @NotNull TagKey<Block> polishingBlockTag, Supplier<ItemStack> result, @Nullable TriConsumer<Level, Player, ItemStack> func) {
        super(crop, build);
        this.polishingBlockTag = polishingBlockTag;
        this.polishingTool = null;
        this.polishingBlock = null;
        this.result = result;
        this.func = func;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (this.getPolishingTool() == null) return super.use(level, player, hand);

        AtomicReference<InteractionResultHolder<ItemStack>> toReturn = new AtomicReference<>();

        ItemHelpers.duoItemFunction(player.getMainHandItem(), player.getOffhandItem(), (pItemStack, pSubItemStack) -> {
            if (this.getPolishingTool().test(pSubItemStack)) {
                pItemStack.shrink(1);
                player.addItem(this.getResult());
                toReturn.set(InteractionResultHolder.success(pItemStack));
                if (this.func != null) this.func.accept(level, player, pItemStack);
                return true;
            }
            toReturn.set(super.use(level, player, hand));
            return false;
        });

        return toReturn.get();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();

        if (player == null) return super.useOn(context);

        if (this.getPolishingBlock() != null && state.is(this.getPolishingBlock())) {
            itemStack.shrink(1);
            player.addItem(this.getResult());
            if (this.func != null) this.func.accept(level, player, itemStack);
        } else if (this.getPolishingBlockTag() != null && state.is(this.getPolishingBlockTag())) {
            itemStack.shrink(1);
            player.addItem(this.getResult());
            if (this.func != null) this.func.accept(level, player, itemStack);
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> component, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, component, flag);

        ResourceLocation itemId = ForgeRegistries.ITEMS.getKey(itemStack.getItem());
        if (itemId == null) return;

        Component itemPolishTool = Component.translatable("item." + itemId.getNamespace() + "." + itemId.getPath() + ".polish_tool").withStyle(ChatFormatting.GRAY);

        if (this.polishingTool != null) {
            component.add(Component.translatable("item.mochi_craft.polishable_item.desc.item.part1").withStyle(ChatFormatting.DARK_GRAY)
                    .append(itemPolishTool)
                    .append(Component.translatable("item.mochi_craft.polishable_item.desc.item.part2").withStyle(ChatFormatting.DARK_GRAY)));
        } else {
            component.add(Component.translatable("item.mochi_craft.polishable_item.desc.block.part1").withStyle(ChatFormatting.DARK_GRAY)
                    .append(itemPolishTool)
                    .append(Component.translatable("item.mochi_craft.polishable_item.desc.block.part2").withStyle(ChatFormatting.DARK_GRAY)));
        }
    }

    @Nullable
    public Ingredient getPolishingTool() {
        return this.polishingTool;
    }

    @Nullable
    public Block getPolishingBlock() {
        return this.polishingBlock;
    }

    @Nullable
    public TagKey<Block> getPolishingBlockTag() {
        return this.polishingBlockTag;
    }

    public ItemStack getResult() {
        return this.result.get();
    }
}
