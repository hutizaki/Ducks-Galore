package com.hutizaki.Ducks_Galore;

import java.util.HashMap;
import java.util.Map;

import com.hutizaki.Ducks_Galore.core.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.MapColor;
import javax.annotation.Nonnull;

public class DuckBlock extends DiodeBlock {
	public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
	public static final BooleanProperty UNLOCK = BooleanProperty.create("unlock");

	public DuckBlock() {
		super(BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_YELLOW)
			.strength(0.2f, 0.2f)
			.sound(SoundType.WOOD));
		this.registerDefaultState(
			this.stateDefinition.any()
				.setValue(POWERED, false)
				.setValue(TRIGGERED, false)
				.setValue(UNLOCK, false)
				.setValue(FACING, Direction.NORTH)
		);
	}

	@Override
	protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWERED, TRIGGERED, UNLOCK);
	}

	@Override
	public InteractionResult use(@Nonnull BlockState state, @Nonnull Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
		if (player.isCrouching()) {
			if (!world.isClientSide) {
				world.playSound(null, pos, Registries.DUCK_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
				return InteractionResult.CONSUME;
			}
			return InteractionResult.SUCCESS;
		}
		return super.use(state, world, pos, player, hand, hit);
	}

	@Override
	protected int getDelay(@Nonnull BlockState state) {
		return 2;
	}

	@Override
	protected int getOutputSignal(@Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull BlockState state) {
		return 15;
	}

	@Override
	public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
		return Shapes.box(0.2, 0.0, 0.2, 0.8, 0.5, 0.8);
	}
} 