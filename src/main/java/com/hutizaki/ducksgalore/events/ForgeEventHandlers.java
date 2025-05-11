package com.hutizaki.ducksgalore.events;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.registry.AllBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Event handler for forge event bus events
 */
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandlers {
    
    /**
     * Handle block break events to potentially drop gold ore rubber ducks
     */
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        Block block = state.getBlock();

        // Check if the broken block is a gold ore block
        if (block.getDescriptionId().equals("block.minecraft.gold_ore") ||
            block.getDescriptionId().equals("block.minecraft.deepslate_gold_ore")) {
            
            // 5% chance to drop a gold ore rubber duck
            if (Math.random() < 0.05) {
                ItemStack duckStack = new ItemStack(AllBlocks.GOLD_ORE_RUBBER_DUCK.get());
                Block.popResource((Level) event.getLevel(), event.getPos(), duckStack);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        BlockState state = event.getPlacedBlock();
        Block block = state.getBlock();

        // Check if the placed block is a gold ore block
        if (block.getDescriptionId().equals("block.minecraft.gold_ore") ||
            block.getDescriptionId().equals("block.minecraft.deepslate_gold_ore")) {
            
            // 5% chance to drop a gold ore rubber duck
            if (Math.random() < 0.05) {
                ItemStack duckStack = new ItemStack(AllBlocks.GOLD_ORE_RUBBER_DUCK.get());
                Block.popResource((Level) event.getLevel(), event.getPos(), duckStack);
            }
        }
    }
} 