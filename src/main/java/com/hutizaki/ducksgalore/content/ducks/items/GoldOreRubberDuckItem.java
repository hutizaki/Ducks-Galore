package com.hutizaki.ducksgalore.content.ducks.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Gold Ore Rubber Duck item implementation
 */
public class GoldOreRubberDuckItem extends BlockItem {
    public GoldOreRubberDuckItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Add tooltip text
        tooltip.add(Component.translatable("tooltip.ducksgalore.gold_ore_rubber_duck"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
} 