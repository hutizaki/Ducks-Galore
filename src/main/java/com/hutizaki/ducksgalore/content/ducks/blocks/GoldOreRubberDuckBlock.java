package com.hutizaki.ducksgalore.content.ducks.blocks;

import com.hutizaki.ducksgalore.registry.AllSoundEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Gold Ore Rubber Duck block implementation - a rare variant found from mining gold ore
 */
public class GoldOreRubberDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    
    // Get the stone sounds directly from registry
    private static final SoundEvent STONE_BREAK = BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("minecraft", "block.stone.break"));
    private static final SoundEvent STONE_PLACE = BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("minecraft", "block.stone.place"));
    
    public GoldOreRubberDuckBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    /**
     * Static creator method based on Minecraft gold ore properties
     */
    public static Properties createGoldOreRubberDuckProperties() {
        return Properties.copy(Blocks.GOLD_ORE)
            .strength(3.0F, 3.0F) // Instant break with proper tool
            .requiresCorrectToolForDrops() // Only drop with proper tool
            .noOcclusion()
            .pushReaction(PushReaction.BLOCK);
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
        // Play gold ore rubber duck sound when right-clicked and give player luck
        if (hand == InteractionHand.MAIN_HAND) {  // Only trigger for main hand
            if (!level.isClientSide) {
                // Give player luck for 30 seconds (600 ticks)
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 600, 0));
                
                // Play gold ore rubber duck quack sound
                level.playSound(null, pos, AllSoundEvents.GOLD_ORE_RUBBER_DUCK_QUACK.get(), SoundSource.BLOCKS, 1.5F, 
                               1.0F + (level.getRandom().nextFloat() * 0.1F));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide) {
                // Use Minecraft's stone break sound instead of custom sound
                level.playSound(null, pos, STONE_BREAK, SoundSource.BLOCKS, 1.0F, 
                              1.0F + (level.getRandom().nextFloat() * 0.1F));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            // Use Minecraft's stone place sound instead of custom sound
            level.playSound(null, pos, STONE_PLACE, SoundSource.BLOCKS, 1.0F, 
                           1.0F + (level.getRandom().nextFloat() * 0.1F));
        }
        super.onPlace(state, level, pos, oldState, isMoving);
    }
} 