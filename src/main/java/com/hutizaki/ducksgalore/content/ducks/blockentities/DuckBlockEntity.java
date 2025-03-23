package com.hutizaki.ducksgalore.content.ducks.blockentities;

import com.hutizaki.ducksgalore.registry.AllBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Block entity for duck blocks
 */
public class DuckBlockEntity extends BlockEntity {
    
    private int quackCounter = 0;
    
    public DuckBlockEntity(BlockPos pos, BlockState state) {
        super(AllBlockEntityTypes.DUCK.get(), pos, state);
    }
    
    /**
     * Server tick handler
     */
    public static void serverTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, DuckBlockEntity blockEntity) {
        // Server-side tick logic here
        blockEntity.tickCounter();
    }
    
    /**
     * Client tick handler
     */
    public static void clientTick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, DuckBlockEntity blockEntity) {
        // Client-side tick logic here
    }
    
    /**
     * Increment quack counter
     */
    private void tickCounter() {
        if (quackCounter > 0) {
            quackCounter--;
        }
    }
    
    /**
     * Get current quack counter
     */
    public int getQuackCounter() {
        return quackCounter;
    }
    
    /**
     * Trigger quack with cooldown
     */
    public void quack() {
        quackCounter = 20; // 1-second cooldown at 20 ticks per second
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("QuackCounter", quackCounter);
    }
    
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        quackCounter = tag.getInt("QuackCounter");
    }
} 