package com.hutizaki.ducksgalore.content.ducks.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

/**
 * Utility class for duck visual effects
 */
public class DuckEffects {
    
    /**
     * Creates enchantment-like particles around a duck block
     */
    public static void createEnchantParticles(Level level, BlockPos pos) {
        createEnchantParticles(level, pos, 15);
    }
    
    /**
     * Creates enchantment-like particles around a duck block with custom count
     */
    public static void createEnchantParticles(Level level, BlockPos pos, int count) {
        RandomSource random = level.getRandom();
        
        for (int i = 0; i < count; i++) {
            double offsetX = random.nextDouble() * 0.6 - 0.3;
            double offsetY = random.nextDouble() * 0.6 - 0.1;
            double offsetZ = random.nextDouble() * 0.6 - 0.3;
            
            level.addParticle(
                ParticleTypes.ENCHANT,
                pos.getX() + 0.5 + offsetX,
                pos.getY() + 0.5 + offsetY,
                pos.getZ() + 0.5 + offsetZ,
                0, 0.05, 0
            );
        }
    }
    
    /**
     * Creates gold/glint particles for gold-based ducks
     */
    public static void createGoldParticles(Level level, BlockPos pos, int count) {
        RandomSource random = level.getRandom();
        
        for (int i = 0; i < count; i++) {
            double offsetX = random.nextDouble() * 0.6 - 0.3;
            double offsetY = random.nextDouble() * 0.6 - 0.1;
            double offsetZ = random.nextDouble() * 0.6 - 0.3;
            
            // Gold particles are a combination of happy villager (green) and dust (yellow)
            level.addParticle(
                ParticleTypes.HAPPY_VILLAGER,
                pos.getX() + 0.5 + offsetX,
                pos.getY() + 0.5 + offsetY,
                pos.getZ() + 0.5 + offsetZ,
                0, 0, 0
            );
        }
    }
    
    /**
     * Creates a small ambient particle effect for gold-based ducks
     * Use this in animateTick for continuous effects
     */
    public static void createAmbientGoldEffect(Level level, BlockPos pos) {
        RandomSource random = level.getRandom();
        
        double offsetX = random.nextDouble() * 0.4 - 0.2;
        double offsetY = random.nextDouble() * 0.4 - 0.2;
        double offsetZ = random.nextDouble() * 0.4 - 0.2;
        
        level.addParticle(
            ParticleTypes.END_ROD,
            pos.getX() + 0.5 + offsetX,
            pos.getY() + 0.5 + offsetY,
            pos.getZ() + 0.5 + offsetZ,
            random.nextDouble() * 0.02 - 0.01,
            random.nextDouble() * 0.02 - 0.01,
            random.nextDouble() * 0.02 - 0.01
        );
    }
} 