package com.hutizaki.ducksgalore.content.ducks.blocks.special;

import com.hutizaki.ducksgalore.content.ducks.blocks.DuckBlock;
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
 * Special golden duck implementation
 */
public class GoldenDuckBlock extends DuckBlock {
    
    public GoldenDuckBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, 
                              @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        // Apply special golden duck effect
        if (!level.isClientSide) {
            // Give player Absorption effect
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 1));
            
            // Play golden duck sound
            level.playSound(player, pos, AllSoundEvents.GOLDEN_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.0F, 
                           level.random.nextFloat() * 0.3F + 0.8F);
        }
        
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
} 