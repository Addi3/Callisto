package com.lib.core.blocks;

import com.lib.core.CallistoBlocks;
import com.lib.core.CallistoSounds;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class PlushBlock extends HorizontalFacingBlock {

    public static final BooleanProperty SQUISHED = BooleanProperty.of("squished");

    protected static final VoxelShape SHAPE =
            Block.createCuboidShape(3, 0, 3, 13, 13, 13);

    public PlushBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(SQUISHED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient) {
            world.setBlockState(pos, state.with(SQUISHED, true), 3);
            world.scheduleBlockTick(pos, this, 6);

            Vec3d mid = Vec3d.ofCenter(pos);
            world.playSound(
                    null,
                    mid.getX(), mid.getY(), mid.getZ(),
                    getSound(state),
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(SQUISHED)) {
            world.setBlockState(pos, state.with(SQUISHED, false), 3);
        }
    }



    public static SoundEvent getSound(BlockState state) {
        return state.getBlock() == CallistoBlocks.PLUSH_ADDIE
                ? CallistoSounds.BOOP
                : SoundEvents.BLOCK_WOOL_HIT;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SQUISHED);
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world,
                                        BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world,
                                      BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
