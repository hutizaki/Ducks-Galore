package com.hutizaki.ducksgalore.content.ducks.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Emerald Rubber Duck item implementation
 */
public class EmeraldRubberDuckItem extends BlockItem {
    
    public EmeraldRubberDuckItem(Block block, Properties properties) {
        super(block, properties);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("item.ducksgalore.emerald_rubber_duck.tooltip"));
    }
} 