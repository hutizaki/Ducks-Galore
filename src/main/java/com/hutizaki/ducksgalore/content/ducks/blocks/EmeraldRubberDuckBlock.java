package com.hutizaki.ducksgalore.content.ducks.blocks;

import com.hutizaki.ducksgalore.content.ducks.util.DuckEffects;
import com.hutizaki.ducksgalore.registry.AllSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Golden Rubber Duck block implementation - a valuable variant with special abilities
 */
public class EmeraldRubberDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    
    public EmeraldRubberDuckBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    /**
     * Static creator method based on Minecraft gold block properties
     */
    public static Properties createEmeraldRubberDuckProperties() {
        return Properties.of()
            .strength(3.05F, 3.0F) // Instant break with proper tool
            .requiresCorrectToolForDrops() // Only drop with proper tool
            .noOcclusion()
            .pushReaction(PushReaction.BLOCK)
            .sound(SoundType.EMPTY);
    }
    
    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        if (player.getMainHandItem().isCorrectToolForDrops(state)) {
            return 999.0F; // Instant break with iron+ pickaxe
        }
        return super.getDestroyProgress(state, player, level, pos); // Slow fallback for wrong tools
    }

    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    public InteractionResult use(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, 
                              @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        // Play golden rubber duck sound when right-clicked and apply effects
        if (hand == InteractionHand.MAIN_HAND) {  // Only trigger for main hand
            if (!level.isClientSide) {
                // Give player regeneration II and absorption II for 30 seconds (600 ticks)
                player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 600, 1));
                
                // Play golden rubber duck quack sound
                level.playSound(null, pos, AllSoundEvents.EMERALD_RUBBER_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.5F, 
                               1.0F + (level.getRandom().nextFloat() * 0.1F));
                
                // Spawn some gold-colored particles
                for (int i = 0; i < 8; i++) {
                    double offsetX = level.getRandom().nextDouble() * 0.5 - 0.25;
                    double offsetY = level.getRandom().nextDouble() * 0.5;
                    double offsetZ = level.getRandom().nextDouble() * 0.5 - 0.25;
                    level.addParticle(ParticleTypes.HAPPY_VILLAGER, 
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
                // Play block event sound
                level.playSound(null, pos, AllSoundEvents.EMERALD_RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                              1.0F + (level.getRandom().nextFloat() * 0.1F));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            // Play block event sound
            level.playSound(null, pos, AllSoundEvents.EMERALD_RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                           1.0F + (level.getRandom().nextFloat() * 0.1F));
        } else {
            // Create particles on client side
            DuckEffects.createEnchantParticles(level, pos, 25);
            DuckEffects.createGoldParticles(level, pos, 15);
        }
        super.onPlace(state, level, pos, oldState, isMoving);
    }
    
    /**
     * Create shimmer/enchant particles at regular intervals
     */
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // More frequent particle generation (1 in 3 chance instead of 1 in 8)
        if (random.nextInt(3) == 0) {
            DuckEffects.createAmbientGoldEffect(level, pos);
        }
        
        // Add enchantment particles occasionally for the shimmer effect
        if (random.nextInt(6) == 0) {
            double x = pos.getX() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
            double y = pos.getY() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
            double z = pos.getZ() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
            
            level.addParticle(
                ParticleTypes.ENCHANT,
                x, y, z,
                0, 0.05, 0
            );
        }
        
        // Add a subtle glow effect with nautilus particles
        if (random.nextInt(10) == 0) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.2;
            double z = pos.getZ() + 0.5;
            
            level.addParticle(
                ParticleTypes.NAUTILUS,
                x, y, z,
                random.nextDouble() * 0.1 - 0.05,
                random.nextDouble() * 0.05 + 0.05,
                random.nextDouble() * 0.1 - 0.05
            );
        }
    }
} 