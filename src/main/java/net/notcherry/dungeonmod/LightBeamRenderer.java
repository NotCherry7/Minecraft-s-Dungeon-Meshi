package net.notcherry.dungeonmod;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import net.notcherry.dungeonmod.block.ModBlocks;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.awt.*;

public class LightBeamRenderer extends RenderType {

	private static final ResourceLocation LOOT_BEAM_TEXTURE = new ResourceLocation(DungeonMod.MOD_ID, "textures/entity/portal_beam.png");
	private static final RenderType LOOT_BEAM_RENDERTYPE = createRenderType();

	ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();



	public LightBeamRenderer(String name, VertexFormat format, VertexFormat.Mode mode, int size, boolean crumble, boolean sorting, Runnable enable, Runnable disable) {
		super(name, format, mode, size, crumble, sorting, enable, disable);
	}

	public static void renderLightBeam(PoseStack stack, MultiBufferSource buffer, float rotation, Color pColor) { //, ItemEntity item) {
//		float beamAlpha = Configuration.BEAM_ALPHA.get().floatValue();
		float beamAlpha = 8.5f;
		//Fade out when close
//		if (Minecraft.getInstance().player.distanceToSqr(item) < 2f) {
//			beamAlpha *= Minecraft.getInstance().player.distanceToSqr(item);
//		}
		//Dont render beam if its too transparent
		if (beamAlpha <= 0.15f) {
			return;
		}

//		float beamRadius = 0.4f * Configuration.BEAM_RADIUS.get().floatValue();
//		float glowRadius = beamRadius + (beamRadius * 0.5f);
//		float beamHeight = Configuration.BEAM_HEIGHT.get().floatValue();
//		float yOffset = Configuration.BEAM_Y_OFFSET.get().floatValue();


		float beamRadius = 0.2f;
		float glowRadius = beamRadius + (beamRadius * 0.5f);
		float beamHeight = 3f;
		float yOffset = 1;


		Color color = pColor;
		float R = color.getRed() / 255f;
		float G = color.getGreen() / 255f;
		float B = color.getBlue() / 255f;

		//I will rewrite the beam rendering code soon! I promise!

		stack.pushPose();

		//Render main beam
		stack.pushPose();
		stack.translate(0, yOffset, 0);
		stack.translate(0.5, 0, 0.5);
		stack.mulPose(Axis.YP.rotationDegrees(rotation * 2.25F - 45.0F));
		stack.mulPose(Axis.XP.rotationDegrees(180));
		renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDERTYPE), R, G, B, beamAlpha, beamHeight, 0.0F, beamRadius, beamRadius, 0.0F, -beamRadius, 0.0F, 0.0F, -beamRadius);
		stack.mulPose(Axis.XP.rotationDegrees(-180));
		renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDERTYPE), R, G, B, beamAlpha, beamHeight, 0.0F, beamRadius, beamRadius, 0.0F, -beamRadius, 0.0F, 0.0F, -beamRadius);
		stack.popPose();

		//Render glow around main beam
//		stack.translate(0.5, yOffset, 0.5);
//		stack.translate(0.5, 1, 0.5);
//		stack.mulPose(Axis.XP.rotationDegrees(180));
//		renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDERTYPE), R, G, B, beamAlpha * 0.4f, beamHeight, -glowRadius, -glowRadius, glowRadius, -glowRadius, -beamRadius, glowRadius, glowRadius, glowRadius);
//		stack.mulPose(Axis.XP.rotationDegrees(-180));
//		renderPart(stack, buffer.getBuffer(LOOT_BEAM_RENDERTYPE), R, G, B, beamAlpha * 0.4f, beamHeight, -glowRadius, -glowRadius, glowRadius, -glowRadius, -beamRadius, glowRadius, glowRadius, glowRadius);
//
		stack.popPose();

	}

	private static void renderPart(PoseStack stack, VertexConsumer builder, float red, float green, float blue, float alpha, float height, float radius_1, float radius_2, float radius_3, float radius_4, float radius_5, float radius_6, float radius_7, float radius_8) {
		PoseStack.Pose matrixentry = stack.last();
		Matrix4f matrixpose = matrixentry.pose();
		Matrix3f matrixnormal = matrixentry.normal();
		renderQuad(matrixpose, matrixnormal, builder, red, green, blue, alpha, height, radius_1, radius_2, radius_3, radius_4);
		renderQuad(matrixpose, matrixnormal, builder, red, green, blue, alpha, height, radius_7, radius_8, radius_5, radius_6);
		renderQuad(matrixpose, matrixnormal, builder, red, green, blue, alpha, height, radius_3, radius_4, radius_7, radius_8);
		renderQuad(matrixpose, matrixnormal, builder, red, green, blue, alpha, height, radius_5, radius_6, radius_1, radius_2);
	}

	private static void renderQuad(Matrix4f pose, Matrix3f normal, VertexConsumer builder, float red, float green, float blue, float alpha, float y, float z1, float texu1, float z, float texu) {
		addVertex(pose, normal, builder, red, green, blue, alpha, y, z1, texu1, 1f, 0f);
		addVertex(pose, normal, builder, red, green, blue, alpha, 0f, z1, texu1, 1f, 1f);
		addVertex(pose, normal, builder, red, green, blue, alpha, 0f, z, texu, 0f, 1f);
		addVertex(pose, normal, builder, red, green, blue, alpha, y, z, texu, 0f, 0f);
	}

	private static void addVertex(Matrix4f pose, Matrix3f normal, VertexConsumer builder, float red, float green, float blue, float alpha, float y, float x, float z, float texu, float texv) {
		builder.vertex(pose, x, y, z).color(red, green, blue, alpha).uv(texu, texv).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
	}

	private static RenderType createRenderType() {
		RenderType.CompositeState state = RenderType.CompositeState.builder().setShaderState(RENDERTYPE_BEACON_BEAM_SHADER).setTextureState(new RenderStateShard.TextureStateShard(LOOT_BEAM_TEXTURE, false, false)).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setWriteMaskState(COLOR_WRITE).createCompositeState(false);
		return RenderType.create("portal_beam", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 256, false, true, state);
	}

	/**
	 * Checks if the player is looking at the given entity, accuracy determines how close the player has to look.
	 */
	private static boolean isLookingAt(LocalPlayer player, Entity target, double accuracy) {
		Vec3 difference = new Vec3(target.getX() - player.getX(), target.getEyeY() - player.getEyeY(), target.getZ() - player.getZ());
		double length = difference.length();
		double dot = player.getViewVector(1.0F).normalize().dot(difference.normalize());
		return dot > 1.0D - accuracy / length && !target.isInvisible();
	}

}