package com.hutizaki.ducksgalore.content.ducks.blocks;

import com.hutizaki.ducksgalore.registry.AllSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nonnull;

/**
 * Golden Rubber Duck block implementation - behaves like a RubberDuck but with golden effects
 */
public class GoldenRubberDuckBlock extends RubberDuckBlock {
    
    public GoldenRubberDuckBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, 
                              @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        // Play golden rubber duck sound when right-clicked and give player effect
        if (hand == InteractionHand.MAIN_HAND) {  // Only trigger for main hand
            // Apply special golden duck effect
            if (!level.isClientSide) {
                // Give player Absorption effect for 1 minute 30 seconds (1800 ticks)
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 1));
                
                // Play golden duck sound with maximum volume
                level.playSound(null, pos, AllSoundEvents.GOLDEN_DUCK_QUACK.get(), SoundSource.BLOCKS, 2.0F, 
                               1.0F + (level.random.nextFloat() * 0.1F));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide) {
                // Play golden rubber duck break sound with maximum volume
                level.playSound(null, pos, AllSoundEvents.GOLDEN_RUBBER_DUCK_BREAK.get(), SoundSource.BLOCKS, 2.0F, 
                              1.0F + (level.random.nextFloat() * 0.1F));
            }
        }
        // Do NOT call super.onRemove to prevent double sound
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        // Do NOT call super.onPlace to prevent double sound
        if (!level.isClientSide) {
            // Play golden rubber duck place sound with maximum volume
            level.playSound(null, pos, AllSoundEvents.GOLDEN_RUBBER_DUCK_PLACE.get(), SoundSource.BLOCKS, 2.0F, 
                           1.0F + (level.random.nextFloat() * 0.1F));
        }
    }
} 