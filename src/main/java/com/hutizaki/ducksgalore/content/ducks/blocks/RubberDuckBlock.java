package com.hutizaki.ducksgalore.content.ducks.blocks;

import com.hutizaki.ducksgalore.registry.AllSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;

/**
 * Base implementation for rubber duck blocks
 */
public class RubberDuckBlock extends DuckBlock {
    
    public RubberDuckBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, 
                              @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        // Play rubber duck sound when right-clicked
        if (!level.isClientSide) {
            level.playSound(player, pos, AllSoundEvents.RUBBER_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.0F, 
                           level.random.nextFloat() * 0.2F + 0.9F);
        }
        
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
} 