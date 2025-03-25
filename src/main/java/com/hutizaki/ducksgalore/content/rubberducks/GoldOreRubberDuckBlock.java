package com.hutizaki.ducksgalore.content.rubberducks;

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
 * Gold Ore Rubber Duck block implementation - a valuable drop-giving variant
 */
public class GoldOreRubberDuckBlock extends HorizontalDirectionalBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    
    public GoldOreRubberDuckBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    /**
     * Static creator method based on Minecraft gold ore properties
     */
    public static Properties createGoldOreRubberDuckProperties() {
        return Properties.of()
            .mapColor(MapColor.STONE)
            .strength(3.0F, 3.0F)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .lightLevel(state -> 3) // Slight glow
            .pushReaction(PushReaction.BLOCK)
            .sound(SoundType.STONE); // Changed from direct reference
    }
    
    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        if (player.getMainHandItem().isCorrectToolForDrops(state)) {
            return 0.2F; // Fast break with iron+ pickaxe
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
        if (hand == InteractionHand.MAIN_HAND) {  // Only trigger for main hand
            if (!level.isClientSide) {
                // Give player minor mining-related effects
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 300, 0)); // 15 sec haste
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0)); // 15 sec night vision
                
                // Play gold ore rubber duck sound
                var soundEntry = AllSoundEvents.GOLD_ORE_RUBBER_DUCK_BLOCK_EVENT;
                level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                               soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
            } else {
                // Client-side particle effects
                for (int i = 0; i < 5; i++) {
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
                // Play appropriate block event sound when removed
                var soundEntry = AllSoundEvents.GOLD_ORE_RUBBER_DUCK_BLOCK_EVENT;
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
            var soundEntry = AllSoundEvents.GOLD_ORE_RUBBER_DUCK_BLOCK_EVENT;
            level.playSound(null, pos, soundEntry.getMainEvent(), SoundSource.BLOCKS, 
                           soundEntry.getVolume(), soundEntry.getPitch() + (level.getRandom().nextFloat() * 0.1F));
        }
        super.onPlace(state, level, pos, oldState, isMoving);
    }
    
    /**
     * Create mining-related particles at intervals
     */
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // Occasionally create gold particle effects
        if (random.nextInt(8) == 0) {
            double x = pos.getX() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
            double y = pos.getY() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
            double z = pos.getZ() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
            
            level.addParticle(
                ParticleTypes.CRIT,
                x, y, z,
                random.nextDouble() * 0.05 - 0.025,
                random.nextDouble() * 0.05 - 0.025,
                random.nextDouble() * 0.05 - 0.025
            );
        }
    }
} 