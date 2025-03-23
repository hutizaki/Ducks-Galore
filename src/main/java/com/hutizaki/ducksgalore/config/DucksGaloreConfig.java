package com.hutizaki.ducksgalore.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

/**
 * Configuration settings for the mod
 */
@Mod.EventBusSubscriber
public class DucksGaloreConfig {
    
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue DUCK_SOUNDS_ENABLED;
    public static final ForgeConfigSpec.IntValue DUCK_EFFECT_DURATION;
    public static final ForgeConfigSpec.DoubleValue GOLDEN_DUCK_BONUS_STRENGTH;
    
    static {
        BUILDER.comment("Configuration settings for Ducks Galore mod");
        
        BUILDER.push("general");
        DUCK_SOUNDS_ENABLED = BUILDER
            .comment("Enable duck sound effects")
            .define("duckSoundsEnabled", true);
        DUCK_EFFECT_DURATION = BUILDER
            .comment("Duration of duck effects in ticks (20 ticks = 1 second)")
            .defineInRange("duckEffectDuration", 600, 20, 24000);
        BUILDER.pop();
        
        BUILDER.push("special_ducks");
        GOLDEN_DUCK_BONUS_STRENGTH = BUILDER
            .comment("Bonus strength multiplier for golden ducks")
            .defineInRange("goldenDuckBonusStrength", 1.5, 1.0, 5.0);
        BUILDER.pop();
        
        SPEC = BUILDER.build();
    }
} 