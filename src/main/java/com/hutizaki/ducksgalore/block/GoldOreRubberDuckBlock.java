package com.hutizaki.ducksgalore.block;

import com.hutizaki.ducksgalore.registry.AllSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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

public class GoldOreRubberDuckBlock extends RubberDuckBlock {
    
    public GoldOreRubberDuckBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, 
                              @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (hand == InteractionHand.MAIN_HAND) {
            if (!level.isClientSide) {
                // Play gold ore duck quack sound
                level.playSound(null, pos, AllSounds.GOLD_ORE_RUBBER_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.5F, 
                               1.0F + (level.getRandom().nextFloat() * 0.1F));
                
                // Give Hero of the Village effect for 20 seconds
                player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 400, 3));
                
                // Spawn gold ore particles
                for (int i = 0; i < 8; i++) {
                    double offsetX = level.getRandom().nextDouble() * 0.5 - 0.25;
                    double offsetY = level.getRandom().nextDouble() * 0.5;
                    double offsetZ = level.getRandom().nextDouble() * 0.5 - 0.25;
                    level.addParticle(ParticleTypes.CRIT, 
                                     pos.getX() + 0.5 + offsetX, 
                                     pos.getY() + 0.5 + offsetY, 
                                     pos.getZ() + 0.5 + offsetZ, 
                                     0, 0, 0);
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide) {
                level.playSound(null, pos, AllSounds.GOLD_ORE_RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                              1.0F + (level.getRandom().nextFloat() * 0.1F));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.playSound(null, pos, AllSounds.GOLD_ORE_RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                           1.0F + (level.getRandom().nextFloat() * 0.1F));
        }
        super.onPlace(state, level, pos, oldState, isMoving);
    }
} 