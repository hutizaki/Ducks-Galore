package com.hutizaki.ducksgalore.content.rubberducks;

import com.hutizaki.ducksgalore.AllSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

/**
 * Regular Rubber Duck block implementation
 */
public class RubberDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    
    // Define shapes for each direction
    private static final VoxelShape SHAPE_NORTH = Shapes.or(
        Block.box(5, 0, 5, 11, 4, 11),    // body
        Block.box(6, 4, 3, 10, 8, 7),     // head
        Block.box(6.4, 4, 1.4, 9.6, 5, 3), // beak
        Block.box(11, 1.4, 6.2, 11.4, 4, 10), // right wing
        Block.box(4.6, 1.4, 6.2, 5, 4, 10),   // left wing
        Block.box(6, 4, 9.8, 10, 5, 10.8)     // tail
    );

    private static final VoxelShape SHAPE_EAST = Shapes.or(
        Block.box(5, 0, 5, 11, 4, 11),    // body
        Block.box(9, 4, 6, 13, 8, 10),    // head
        Block.box(13, 4, 6.4, 14.6, 5, 9.6), // beak
        Block.box(6, 1.4, 11, 9.8, 4, 11.4), // right wing
        Block.box(6, 1.4, 4.6, 9.8, 4, 5),   // left wing
        Block.box(5.2, 4, 6, 6.2, 5, 10)     // tail
    );

    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
        Block.box(5, 0, 5, 11, 4, 11),    // body
        Block.box(6, 4, 9, 10, 8, 13),    // head
        Block.box(6.4, 4, 13, 9.6, 5, 14.6), // beak
        Block.box(4.6, 1.4, 6, 5, 4, 9.8),   // right wing
        Block.box(11, 1.4, 6, 11.4, 4, 9.8), // left wing
        Block.box(6, 4, 5.2, 10, 5, 6.2)     // tail
    );

    private static final VoxelShape SHAPE_WEST = Shapes.or(
        Block.box(5, 0, 5, 11, 4, 11),    // body
        Block.box(3, 4, 6, 7, 8, 10),     // head
        Block.box(1.4, 4, 6.4, 3, 5, 9.6),  // beak
        Block.box(6.2, 1.4, 4.6, 10, 4, 5),  // right wing
        Block.box(6.2, 1.4, 11, 10, 4, 11.4),// left wing
        Block.box(9.8, 4, 6, 10.8, 5, 10)    // tail
    );
    
    public RubberDuckBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    /**
     * Static creator method with default Minecraft properties
     */
    public static Properties createRubberDuckProperties() {
        return Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .strength(0.05F)  // Easily breakable by hand
            .noOcclusion();
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
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return false;
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                              Player player, InteractionHand hand, BlockHitResult hit) {
        // Play rubber duck sound when right-clicked and give player absorption
        if (hand == InteractionHand.MAIN_HAND) {  // Only trigger for main hand
            if (!level.isClientSide) {
                // Play rubber duck quack sound
                var soundEntry = AllSoundEvents.RUBBER_DUCK_QUACK;
                level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                               soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
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
                var soundEntry = AllSoundEvents.RUBBER_DUCK_BLOCK_EVENT;
                level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                              soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            // Play block event sound
            var soundEntry = AllSoundEvents.RUBBER_DUCK_BLOCK_EVENT;
            level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                           soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
        }
        super.onPlace(state, level, pos, oldState, isMoving);
    }
} 