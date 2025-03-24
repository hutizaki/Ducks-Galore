package com.hutizaki.ducksgalore.client;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.registry.AllBlocks;
import com.hutizaki.ducksgalore.registry.AllItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client-side initialization for Ducks Galore mod
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = DucksGalore.MOD_ID, value = Dist.CLIENT)
public class DucksGaloreClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DucksGaloreClient.class);

    /**
     * Initialize client-side components
     */
    public static void init() {
        LOGGER.info("DucksGaloreClient: Initializing client components...");
        
        // Register client setup event
        MinecraftForge.EVENT_BUS.register(DucksGaloreClient.class);
    }

    /**
     * Register render types for blocks
     */
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("DucksGaloreClient: Setting up render types...");
            
            // Set up render types for blocks
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.RUBBER_DUCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.GOLDEN_RUBBER_DUCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(AllBlocks.GOLD_ORE_RUBBER_DUCK.get(), RenderType.cutout());
        });
    }
} 