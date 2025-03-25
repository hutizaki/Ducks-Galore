package com.hutizaki.ducksgalore.config;

import com.hutizaki.ducksgalore.DucksGalore;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Configuration handler for Ducks Galore mod
 * Uses ForgeConfigSpec for clean configuration management
 */
public class DucksGaloreConfig {
    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        // Setup Common configuration
        final Pair<CommonConfig, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder()
                .configure(CommonConfig::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();

        // Setup Client-side configuration
        final Pair<ClientConfig, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder()
                .configure(ClientConfig::new);
        CLIENT = clientPair.getLeft();
        CLIENT_SPEC = clientPair.getRight();
    }

    /**
     * Register all configuration specifications with Forge
     */
    public static void register() {
        ModLoadingContext context = ModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
        
        DucksGalore.LOGGER.info("Ducks Galore config initialized");
    }

    /**
     * Common configuration settings for both client and server
     */
    public static class CommonConfig {
        // Duck spawn weights and generation settings
        public final ForgeConfigSpec.IntValue rubberDuckRarity;
        public final ForgeConfigSpec.IntValue goldenDuckRarity;
        public final ForgeConfigSpec.IntValue goldOreDuckRarity;
        
        // Duck special effects
        public final ForgeConfigSpec.BooleanValue enableGoldenDuckEffects;
        public final ForgeConfigSpec.BooleanValue enableGoldOreDuckEffects;
        public final ForgeConfigSpec.IntValue effectDurationSeconds;
        
        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Common configuration settings for Ducks Galore")
                   .push("common");

            builder.comment("Duck spawn weights and generation settings")
                   .push("spawning");
            
            rubberDuckRarity = builder
                .comment("Rarity of rubber ducks when generated in loot chests (higher = more common)")
                .defineInRange("rubberDuckRarity", 15, 1, 100);
                
            goldenDuckRarity = builder
                .comment("Rarity of golden rubber ducks when generated in loot chests (higher = more common)")
                .defineInRange("goldenDuckRarity", 5, 1, 100);
                
            goldOreDuckRarity = builder
                .comment("Rarity of gold ore rubber ducks when generated from mining gold ore (higher = more common)")
                .defineInRange("goldOreDuckRarity", 8, 1, 100);
                
            builder.pop();
            
            builder.comment("Duck special effects settings")
                   .push("effects");
                   
            enableGoldenDuckEffects = builder
                .comment("Whether golden ducks provide special effects when right-clicked")
                .define("enableGoldenDuckEffects", true);
                
            enableGoldOreDuckEffects = builder
                .comment("Whether gold ore ducks provide special effects when right-clicked")
                .define("enableGoldOreDuckEffects", true);
                
            effectDurationSeconds = builder
                .comment("Duration of effects granted by special ducks in seconds")
                .defineInRange("effectDurationSeconds", 30, 5, 300);
                
            builder.pop();
            
            builder.pop();
        }
    }

    /**
     * Client-side only configuration
     */
    public static class ClientConfig {
        // Visual and audio settings
        public final ForgeConfigSpec.BooleanValue enableParticleEffects;
        public final ForgeConfigSpec.DoubleValue duckSoundVolume;
        public final ForgeConfigSpec.BooleanValue enableAnimations;
        
        public ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Client-side configuration settings for Ducks Galore")
                   .push("client");

            builder.comment("Visual and audio settings")
                   .push("visual");
            
            enableParticleEffects = builder
                .comment("Whether to show particle effects for special ducks")
                .define("enableParticleEffects", true);
                
            duckSoundVolume = builder
                .comment("Volume multiplier for duck sounds (1.0 = normal)")
                .defineInRange("duckSoundVolume", 1.0, 0.0, 2.0);
                
            enableAnimations = builder
                .comment("Whether to enable GeckoLib animations for ducks (if available)")
                .define("enableAnimations", true);
                
            builder.pop();
            
            builder.pop();
        }
    }
} 