package com.hutizaki.ducksgalore.events;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.registry.AllItems;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

/**
 * Event handler for forge event bus events
 */
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandlers {
    
    private static final Random RANDOM = new Random();
    
    /**
     * Handle right-click events for interaction with duck blocks
     */
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        // Event handling code for duck blocks
    }
    
    /**
     * Handle block break events to potentially drop gold ore rubber ducks
     */
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        // Only check on server side
        if (event.getLevel().isClientSide()) return;
        
        Level level = (Level) event.getLevel();
        
        // Check for gold ore
        if (event.getState().getBlock() == Blocks.GOLD_ORE) {
            // 0.05% chance (1/2000) to drop a gold ore rubber duck
            if (RANDOM.nextInt(2000) == 0) {
                ItemStack duckStack = new ItemStack(AllItems.GOLD_ORE_RUBBER_DUCK.get());
                ItemEntity itemEntity = new ItemEntity(
                    level,
                    event.getPos().getX() + 0.5,
                    event.getPos().getY() + 0.5,
                    event.getPos().getZ() + 0.5,
                    duckStack
                );
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
        }
        // Check for deepslate gold ore - higher chance
        else if (event.getState().getBlock() == Blocks.DEEPSLATE_GOLD_ORE) {
            // 0.1% chance (1/1000) to drop a gold ore rubber duck
            if (RANDOM.nextInt(1000) == 0) {
                ItemStack duckStack = new ItemStack(AllItems.GOLD_ORE_RUBBER_DUCK.get());
                ItemEntity itemEntity = new ItemEntity(
                    level,
                    event.getPos().getX() + 0.5,
                    event.getPos().getY() + 0.5,
                    event.getPos().getZ() + 0.5,
                    duckStack
                );
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            }
        }
    }
} 