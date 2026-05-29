package net.mofusya.mochi_craft;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mofusya.mochi_craft.blocks.ModBlocks;
import net.mofusya.mochi_craft.items.ModItems;
import net.mofusya.mochi_craft.items.ModTabs;
import org.slf4j.Logger;

@Mod(MochiCraft.MOD_ID)
public class MochiCraft
{
    public static final String MOD_ID = "mochi_craft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MochiCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModTabs.TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        /*
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
         */
    }
}
