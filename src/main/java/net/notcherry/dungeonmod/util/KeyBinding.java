package net.notcherry.dungeonmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_DUNGEONMOD = "key.category.dungeonmod.dungeon";
    public static final String KEY_CONSUME_MANA = "key.dungeonmod.consume_mana";
    public static final String KEY_SELECT_SPELL = "key.dungeonmod.select_spell";

    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_CONSUME_MANA, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_DUNGEONMOD);

    public static final KeyMapping SPELL_WHEEL_KEY = new KeyMapping(KEY_SELECT_SPELL, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_DUNGEONMOD);
}
