package com.hutizaki.ducksgalore.client;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.client.render.blockentity.DuckBlockEntityRenderer;
import com.hutizaki.ducksgalore.client.render.entity.DuckEntityRenderer;
import com.hutizaki.ducksgalore.registry.AllBlockEntityTypes;
import com.hutizaki.ducksgalore.registry.AllEntityTypes;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Client-side initialization
 */
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DucksGaloreClient {
    
    /**
     * Initialize client-side components
     */
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Register entity renderers
            EntityRenderers.register(AllEntityTypes.DUCK.get(), DuckEntityRenderer::new);
        });
    }
    
    /**
     * Register block entity renderers
     */
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AllBlockEntityTypes.DUCK.get(), DuckBlockEntityRenderer::new);
    }
} 