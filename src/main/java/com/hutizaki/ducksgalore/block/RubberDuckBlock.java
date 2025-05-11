package com.hutizaki.ducksgalore.block;

import com.hutizaki.ducksgalore.registry.AllSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RubberDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    
    public RubberDuckBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
        if (hand == InteractionHand.MAIN_HAND) {
            if (!level.isClientSide) {
                // Play quack sound
                level.playSound(null, pos, AllSounds.RUBBER_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.5F, 
                               1.0F + (level.getRandom().nextFloat() * 0.1F));
                
                // Give Hero of the Village effect for 5 seconds
                player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 100, 0));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide) {
                level.playSound(null, pos, AllSounds.RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                              1.0F + (level.getRandom().nextFloat() * 0.1F));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.playSound(null, pos, AllSounds.RUBBER_DUCK_BLOCK_EVENT.get(), SoundSource.BLOCKS, 1.5F, 
                           1.0F + (level.getRandom().nextFloat() * 0.1F));
        } else {
            // Create particles on client side
            for (int i = 0; i < 25; i++) {
                double offsetX = level.getRandom().nextDouble() * 0.5 - 0.25;
                double offsetY = level.getRandom().nextDouble() * 0.5;
                double offsetZ = level.getRandom().nextDouble() * 0.5 - 0.25;
                level.addParticle(ParticleTypes.ENCHANT, 
                                 pos.getX() + 0.5 + offsetX, 
                                 pos.getY() + 0.5 + offsetY, 
                                 pos.getZ() + 0.5 + offsetZ, 
                                 0, 0, 0);
            }
        }
        super.onPlace(state, level, pos, oldState, isMoving);
    }
} 