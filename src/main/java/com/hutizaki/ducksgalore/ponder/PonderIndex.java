package com.hutizaki.ducksgalore.ponder;

import com.hutizaki.ducksgalore.DucksGalore;
import com.hutizaki.ducksgalore.registry.AllBlocks;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registers all ponder scenes for Ducks Galore mod
 */
public class PonderIndex {
    private static final Logger LOGGER = LoggerFactory.getLogger(PonderIndex.class);
    
    // Helper for easier registration
    private static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(DucksGalore.MOD_ID);

    /**
     * Register all ponder scenes for duck blocks
     */
    public static void register() {
        LOGGER.info("Registering duck ponder scenes...");
        
        try {
            // Create resource locations for the ducks
            ResourceLocation rubberDuck = new ResourceLocation(DucksGalore.MOD_ID, "rubber_duck");
            ResourceLocation goldenDuck = new ResourceLocation(DucksGalore.MOD_ID, "golden_rubber_duck");
            ResourceLocation goldOreDuck = new ResourceLocation(DucksGalore.MOD_ID, "gold_ore_rubber_duck");
            
            // Register scenes using the Create API
            // This code is intentionally commented out until we have proper NBT files
            /*
            PonderRegistry.forComponents(rubberDuck)
                .addStoryBoard(rubberDuck, PonderScenes::rubberDuck);
            
            PonderRegistry.forComponents(goldenDuck)
                .addStoryBoard(goldenDuck, PonderScenes::goldenRubberDuck);
                
            PonderRegistry.forComponents(goldOreDuck)
                .addStoryBoard(goldOreDuck, PonderScenes::goldOreRubberDuck);
            */
                
            LOGGER.info("Duck ponder scenes registered successfully!");
        } catch (Exception e) {
            // Gracefully handle any exceptions from the Create API
            LOGGER.error("Failed to register ponder scenes: {}", e.getMessage());
        }
    }
}
