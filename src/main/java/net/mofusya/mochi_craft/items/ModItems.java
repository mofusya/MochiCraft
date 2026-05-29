package net.mofusya.mochi_craft.items;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.mochi_craft.blocks.ModBlocks;
import net.mofusya.mochi_craft.items.item.*;
import net.mofusya.ornatelib.item.DescriptionItem;
import net.mofusya.ornatelib.registries.OrnateItemDeferredRegister;

public class ModItems {
    public static final OrnateItemDeferredRegister ITEMS = OrnateItemDeferredRegister.create(MochiCraft.MOD_ID);

    public static final RegistryObject<Item> UNPOLISHED_RICE = ITEMS.register("unpolished_rice", build ->
            new ItemNameBlockPolishableItem(ModBlocks.RICE_CROP.get(), build, Ingredient.of(ItemTags.SWORDS), () -> new ItemStack(ModItems.RICE.get())));

    public static final RegistryObject<Item> RICE = ITEMS.register("rice", DescriptionItem::new);

    public static final RegistryObject<Item> COOKED_RICE = ITEMS.register("cooked_rice", Item::new,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.2f).build()));

    public static final RegistryObject<Item> MOCHI = ITEMS.register("mochi", build ->
                    new PolishableChokingItem(build, Ingredient.of(ItemTags.SWORDS), () -> new ItemStack(ModItems.CUT_MOCHI.get()), 30),
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
}
