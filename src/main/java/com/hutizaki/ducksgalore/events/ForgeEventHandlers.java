package com.hutizaki.ducksgalore.events;

import com.hutizaki.ducksgalore.DucksGalore;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Event handler for forge event bus events
 */
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandlers {
    
    /**
     * Handle right-click events for interaction with duck blocks
     */
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        // Event handling code for duck blocks
    }
} 