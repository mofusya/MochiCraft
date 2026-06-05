package net.mofusya.mochi_craft.component;

import net.mofusya.mochi_craft.MochiCraft;
import net.mofusya.ornatelib.registries.component.TagRegisterer;
import net.mofusya.ornatelib.registries.component.tag.IntegerTag;

public class ModComponents {
    public static final TagRegisterer TAGS = new TagRegisterer(MochiCraft.MOD_ID);

    public static final IntegerTag SHARPEN = TAGS.register(new IntegerTag("sharpen"));
}
