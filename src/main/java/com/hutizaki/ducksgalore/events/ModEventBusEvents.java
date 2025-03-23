package com.hutizaki.ducksgalore.events;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.client.render.model.DuckModel;
import com.hutizaki.ducksgalore.content.ducks.entities.DuckEntity;
import com.hutizaki.ducksgalore.registry.AllEntityTypes;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Event handler for mod event bus events
 */
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    
    /**
     * Register entity attributes
     */
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(AllEntityTypes.DUCK.get(), DuckEntity.createAttributes().build());
    }
    
    /**
     * Register model layer definitions
     */
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DuckModel.LAYER_LOCATION, DuckModel::createBodyLayer);
    }
} 