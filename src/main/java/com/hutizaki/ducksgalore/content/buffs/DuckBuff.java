package com.hutizaki.ducksgalore.content.buffs;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Interface for duck buff implementations
 */
public interface DuckBuff {
    
    /**
     * Get the unique identifier for this buff
     */
    ResourceLocation getId();
    
    /**
     * Get the translation key for this buff
     */
    String getTranslationKey();
    
    /**
     * Get the item stack to display for this buff in the GUI
     */
    ItemStack getDisplayIcon();
    
    /**
     * Apply the buff effect to the player
     * 
     * @param player The player to apply the effect to
     * @param level The level
     * @param pos The position of the duck block
     */
    void applyEffect(Player player, Level level, BlockPos pos);
} 