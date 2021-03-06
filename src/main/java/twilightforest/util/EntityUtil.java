package twilightforest.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.function.DoubleUnaryOperator;

public class EntityUtil {

	public static boolean canDestroyBlock(World world, BlockPos pos, Entity entity) {
		return canDestroyBlock(world, pos, world.getBlockState(pos), entity);
	}

	public static boolean canDestroyBlock(World world, BlockPos pos, BlockState state, Entity entity) {
		float hardness = state.getBlockHardness(world, pos);
		return hardness >= 0f && hardness < 50f && !state.getBlock().isAir(state, world, pos)
				&& state.getBlock().canEntityDestroy(state, world, pos, entity)
				&& (/* rude type limit */!(entity instanceof LivingEntity)
				|| ForgeEventFactory.onEntityDestroyBlock((LivingEntity) entity, pos, state));
	}

	/**
	 * [VanillaCopy] Exact copy of Entity.rayTrace
	 * TODO: update it?
	 */
	@Nullable
	public static RayTraceResult rayTrace(Entity entity, double range) {
		Vec3d position = entity.getEyePosition(1.0F);
		Vec3d look = entity.getLook(1.0F);
		Vec3d dest = position.add(look.x * range, look.y * range, look.z * range);
		return entity.world.rayTraceBlocks(position, dest);
	}

	@Nullable
	public static RayTraceResult rayTrace(PlayerEntity player) {
		return rayTrace(player, null);
	}

	@Nullable
	public static RayTraceResult rayTrace(PlayerEntity player, @Nullable DoubleUnaryOperator modifier) {
		double range = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();
		return rayTrace(player, modifier == null ? range : modifier.applyAsDouble(range));
	}
}
