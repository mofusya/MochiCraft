package net.mofusya.mochi_craft.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.mofusya.mochi_craft.MochiCraft;

public class ServerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue RICE_DROP_PROP;
    public static final ForgeConfigSpec.IntValue SOY_BEAN_DROP_PROP;

    static {
        BUILDER.push(MochiCraft.MOD_ID + "-server");

        RICE_DROP_PROP = BUILDER.comment("The chance in percentage of whether a Unpolished Rice will drop from a grass [default: 10, range: 0 ~ 100]").defineInRange("unpolished_rice_drop_chance", 10, 0, 100);
        SOY_BEAN_DROP_PROP = BUILDER.comment("The chance in percentage of whether a Soy Bean will drop from a grass [default: 10, range: 0 ~ 100]").defineInRange("soy_bean_drop_chance", 10, 0, 100);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
