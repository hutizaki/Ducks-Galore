package com.hutizaki.ducksgalore.util;

import com.hutizaki.ducksgalore.content.ducks.blockentities.DuckBlockEntity;
import com.hutizaki.ducksgalore.registry.AllBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Helper methods for duck-related functionality
 */
public class DuckHelper {
    
    /**
     * Check if a block entity is a duck block entity
     */
    public static boolean isDuckBlockEntity(BlockEntity blockEntity) {
        return blockEntity != null && blockEntity.getType() == AllBlockEntityTypes.DUCK.get();
    }
    
    /**
     * Get the duck block entity at the given position, or null if not found
     */
    public static DuckBlockEntity getDuckBlockEntity(Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return isDuckBlockEntity(blockEntity) ? (DuckBlockEntity) blockEntity : null;
    }
    
    /**
     * Check if a duck is on cooldown for quacking
     */
    public static boolean isDuckOnCooldown(Level level, BlockPos pos) {
        DuckBlockEntity duckBlockEntity = getDuckBlockEntity(level, pos);
        return duckBlockEntity != null && duckBlockEntity.getQuackCounter() > 0;
    }
    
    /**
     * Make a duck quack
     */
    public static void quack(Level level, BlockPos pos) {
        DuckBlockEntity duckBlockEntity = getDuckBlockEntity(level, pos);
        if (duckBlockEntity != null) {
            duckBlockEntity.quack();
        }
    }
} 