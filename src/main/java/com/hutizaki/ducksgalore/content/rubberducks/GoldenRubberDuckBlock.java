package com.hutizaki.ducksgalore.content.rubberducks;

import java.util.Properties;

import com.hutizaki.ducksgalore.AllSoundEvents;

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

/**
 * Golden Rubber Duck block implementation - a valuable variant with special abilities
 */
public class GoldenRubberDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    
    public GoldenRubberDuckBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    /**
     * Static creator method based on Minecraft gold block properties
     */
    public static Properties createGoldenRubberDuckProperties() {
        return Properties.of()
            .mapColor(MapColor.GOLD)
            .strength(3.05F, 3.0F) // Instant break with proper tool
            .requiresCorrectToolForDrops() // Only drop with proper tool
            .noOcclusion()
            .lightLevel(state -> 8) // Add light emission
            .pushReaction(PushReaction.BLOCK)
            .sound(SoundType.METAL); // Changed from invalid reference
    }
    
    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        if (player.getMainHandItem().isCorrectToolForDrops(state)) {
            return 0.25F; // Instant break with iron+ pickaxe
        }
        return super.getDestroyProgress(state, player, level, pos); // Slow fallback for wrong tools
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, 
                              Player player, InteractionHand hand, BlockHitResult hit) {
        // Play golden rubber duck sound when right-clicked and apply effects
        if (hand == InteractionHand.MAIN_HAND) {  // Only trigger for main hand
            if (!level.isClientSide) {
                // Give player regeneration II and absorption II for 30 seconds (600 ticks)
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 1));
                
                // Play golden rubber duck quack sound
                var soundEntry = AllSoundEvents.GOLDEN_DUCK_QUACK;
                level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                              soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
            } else {
                // Client-side particle effects
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
                // Play appropriate block event sound when removed
                var soundEntry = AllSoundEvents.GOLDEN_RUBBER_DUCK_BLOCK_EVENT;
                level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                              soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            // Play appropriate block event sound when placed
            var soundEntry = AllSoundEvents.GOLDEN_RUBBER_DUCK_BLOCK_EVENT;
            level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                           soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
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
            createAmbientGoldEffect(level, pos, random);
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
    
    /**
     * Create ambient gold particle effect
     */
    private void createAmbientGoldEffect(Level level, BlockPos pos, RandomSource random) {
        double x = pos.getX() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
        double y = pos.getY() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
        double z = pos.getZ() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
        
        level.addParticle(
            ParticleTypes.END_ROD,
            x, y, z,
            random.nextDouble() * 0.05 - 0.025,
            random.nextDouble() * 0.05 - 0.025,
            random.nextDouble() * 0.05 - 0.025
        );
    }
    
    /**
     * Create gold particles around a position
     */
    private void createGoldParticles(Level level, BlockPos pos, int count) {
        RandomSource random = level.getRandom();
        for (int i = 0; i < count; i++) {
            double x = pos.getX() + 0.5 + (random.nextDouble() * 0.8 - 0.4);
            double y = pos.getY() + 0.5 + (random.nextDouble() * 0.8 - 0.4);
            double z = pos.getZ() + 0.5 + (random.nextDouble() * 0.8 - 0.4);
            
            level.addParticle(
                ParticleTypes.END_ROD,
                x, y, z,
                random.nextDouble() * 0.1 - 0.05,
                random.nextDouble() * 0.1 - 0.05,
                random.nextDouble() * 0.1 - 0.05
            );
        }
    }
} 