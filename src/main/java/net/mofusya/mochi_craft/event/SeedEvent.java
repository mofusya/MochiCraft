package net.mofusya.mochi_craft.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mofusya.mochi_craft.config.ServerConfig;
import net.mofusya.mochi_craft.items.ModItems;

@Mod.EventBusSubscriber
public class SeedEvent {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel() instanceof ServerLevel server && isGrass(event.getState())) {
            BlockPos pos = event.getPos();
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();

            if (Mth.nextInt(RandomSource.create(), 0, 100) <= ServerConfig.RICE_DROP_PROP.get()) {
                server.addFreshEntity(new ItemEntity(server, x, y, z, new ItemStack(ModItems.UNPOLISHED_RICE.get())));
            }

            if (Mth.nextInt(RandomSource.create(), 0, 100) <= ServerConfig.SOY_BEAN_DROP_PROP.get()) {
                server.addFreshEntity(new ItemEntity(server, x, y, z, new ItemStack(ModItems.SOY_BEAN.get())));
            }
        }
    }

    public static boolean isGrass(BlockState state) {
        return state.is(Blocks.GRASS) || state.is(Blocks.TALL_GRASS);
    }
}
