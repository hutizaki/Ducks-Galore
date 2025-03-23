package com.hutizaki.ducksgalore.content.ducks.items.special;

import com.hutizaki.ducksgalore.content.ducks.items.DuckItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Special golden duck item implementation
 */
public class GoldenDuckItem extends DuckItem {
    
    public GoldenDuckItem(Block block, Properties properties) {
        super(block, properties.rarity(net.minecraft.world.item.Rarity.RARE));
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        
        // Add special tooltip information
        tooltip.add(Component.translatable("tooltip.ducksgalore.golden_duck")
                   .withStyle(ChatFormatting.GOLD));
    }
    
    // Additional golden duck item functionality can be added here
} 