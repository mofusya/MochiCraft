package net.mofusya.mochi_craft.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.blocks.ModBlocks;
import net.mofusya.mochi_craft.items.item.*;
import net.mofusya.ornatelib.registries.OrnateItemDeferredRegister;
import net.mofusya.ornatelib.registries.toolset.ToolSet;

public class ModItems {
    public static final OrnateItemDeferredRegister ITEMS = OrnateItemDeferredRegister.create(MochiCraft.MOD_ID, 2);

    public static final RegistryObject<Item> UNPOLISHED_RICE = ITEMS.register("unpolished_rice", build ->
            new ItemNameBlockPolishableItem(ModBlocks.RICE_CROP.get(), build, Ingredient.of(ItemTags.SWORDS), () -> new ItemStack(ModItems.RICE.get()),
                    (level, player, itemStack) -> {
                        if (level.isClientSide) player.playSound(SoundEvents.BLAZE_HURT);
                    }));

    public static final RegistryObject<Item> RICE = ITEMS.register("rice");

    public static final RegistryObject<Item> COOKED_RICE = ITEMS.register("cooked_rice", Item::new,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.2f).build()));

    public static final RegistryObject<Item> BIT_OF_MOCHI = ITEMS.register("bit_of_mochi", Item::new,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(1.5f).build()));

    public static final RegistryObject<Item> MOCHI = ITEMS.register("mochi", build ->
                    new PolishableChokingItem(build, Ingredient.of(ItemTags.SWORDS), () -> new ItemStack(ModItems.CUT_MOCHI.get()),
                            (level, player, itemStack) -> {
                                if (level.isClientSide) player.playSound(SoundEvents.BLAZE_HURT);
                            }, 30),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(2f).build()));

    public static final RegistryObject<Item> CUT_MOCHI = ITEMS.register("cut_mochi", build -> new ChokingItem(build, 20),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(2f).build()));

    public static final RegistryObject<Item> COOKED_CUT_MOCHI = ITEMS.register("cooked_cut_mochi", build -> new ChokingItem(build, 20),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(4f).build()));

    public static final RegistryObject<Item> WELL_COOKED_CUT_MOCHI = ITEMS.register("well_cooked_cut_mochi", build -> new ChokingItem(build, 10),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(4f).build()));

    public static final RegistryObject<Item> OVERCOOKED_CUT_MOCHI = ITEMS.register("overcooked_cut_mochi", build -> new BurningItem(build, 30),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1f).build()));

    public static final RegistryObject<Item> CARBON = ITEMS.register("carbon", build -> new FuelItem(build, 64));

    public static final RegistryObject<Item> SOY_BEAN = ITEMS.register("soy_bean", build ->
            new ItemNameBlockPolishableItem(ModBlocks.SOY_BEAN_CROP.get(), build, Blocks.GRINDSTONE, () -> new ItemStack(ModItems.KINAKO.get()),
                    (level, player, itemStack) -> {
                        if (level.isClientSide) player.playSound(SoundEvents.GRINDSTONE_USE);
                    }));

    public static final RegistryObject<Item> KINAKO = ITEMS.register("kinako", Item::new);

    public static final RegistryObject<Item> KINAKO_MOCHI = ITEMS.register("kinako_mochi", build -> new ChokingItem(build, 20),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(2f).build()));

    public static final RegistryObject<Item> MOCHI_UPGRADE_TEMPLATE = ITEMS.register("mochi_upgrade_template", build -> new MultiDescriptionItem(build, 6));

    public static final ToolSet MOCHI_TOOLS = ITEMS.register("mochi",
            new ToolSet.Builder(ModBlocks.MOCHI_BLOCK::get)
                    .property(new Item.Properties().rarity(Rarity.RARE))
                    .attackDamage(12)
                    .attackSpeed(1.8f)
                    .durability(4096)
                    .toolLevel(5)
                    .swordFunc(UpgradeSwordItem::new)
                    .axeFunc(UpgradeAxeItem::new)
                    .pickaxeFunc(UpgradePickaxeItem::new)
                    .shovelFunc(UpgradeShovelItem::new)
                    .hoeFunc(UpgradeHoeItem::new), 1);
}