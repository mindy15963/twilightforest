package twilightforest.client.model.entity;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import twilightforest.entity.EntityTFTroll;

public class ModelTFTroll<T extends EntityTFTroll> extends BipedModel<T> {

	public ModelRenderer nose;

	public ModelTFTroll() {
		super(0.0F, 0.0F, 128, 64);

//		this.textureWidth = 128;
//		this.textureHeight = 64;

		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addCuboid(-5.0F, -8.0F, -3.0F, 10, 10, 10);
		this.bipedHead.setRotationPoint(0.0F, -9.0F, -6.0F);

		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.addCuboid(-4.0F, -8.0F, -4.0F, 0, 0, 0);

		this.bipedBody = new ModelRenderer(this, 40, 0);
		this.bipedBody.addCuboid(-8.0F, 0.0F, -5.0F, 16, 26, 10);
		this.bipedBody.setRotationPoint(0.0F, -14.0F, 0.0F);


		this.nose = new ModelRenderer(this, 0, 21);
		this.nose.addCuboid(-2.0F, -2.0F, -2.0F, 4, 8, 4);
		this.nose.setRotationPoint(0.0F, -2.0F, -4.0F);
		this.bipedHead.addChild(nose);

		this.bipedRightArm = new ModelRenderer(this, 32, 36);
		this.bipedRightArm.addCuboid(-5.0F, -2.0F, -3.0F, 6, 22, 6);
		this.bipedRightArm.setRotationPoint(-9.0F, -9.0F, 0.0F);

		this.bipedLeftArm = new ModelRenderer(this, 32, 36);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addCuboid(-1.0F, -2.0F, -3.0F, 6, 22, 6);
		this.bipedLeftArm.setRotationPoint(9.0F, -9.0F, 0.0F);


		this.bipedRightLeg = new ModelRenderer(this, 0, 44);
		this.bipedRightLeg.addCuboid(-3.0F, 0.0F, -4.0F, 6, 12, 8);
		this.bipedRightLeg.setRotationPoint(-5.0F, 12.0F, 0.0F);

		this.bipedLeftLeg = new ModelRenderer(this, 0, 44);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addCuboid(-3.0F, 0.0F, -4.0F, 6, 12, 8);
		this.bipedLeftLeg.setRotationPoint(5.0F, 12.0F, 0.0F);
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, limbSwing and limbSwingAmount are used for animating the movement of arms
	 * and legs, where limbSwing represents the time(so that arms and legs swing back and forth) and limbSwingAmount represents how
	 * "far" arms and legs can swing at most.
	 */
	@Override
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.bipedHead.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		this.bipedHead.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
		this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
		this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.bipedRightLeg.rotateAngleY = 0.0F;
		this.bipedLeftLeg.rotateAngleY = 0.0F;

		if (entity.isBeingRidden()) {
			// arms up!
			this.bipedRightArm.rotateAngleX += Math.PI;
			this.bipedLeftArm.rotateAngleX += Math.PI;

		}

		if (this.leftArmPose != ArmPose.EMPTY) {
			this.bipedRightArm.rotateAngleX += Math.PI;
		}
		if (this.rightArmPose != ArmPose.EMPTY) {
			this.bipedLeftArm.rotateAngleX += Math.PI;
		}

		if (this.swingProgress > 0F) {
			float swing = 1.0F - this.swingProgress;

			this.bipedRightArm.rotateAngleX -= (Math.PI * swing);
			this.bipedLeftArm.rotateAngleX -= (Math.PI * swing);
		}

		this.bipedRightArm.rotateAngleY = 0.0F;
		this.bipedLeftArm.rotateAngleY = 0.0F;

		this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}

	/**
	 * Change eye color if yeti is angry
	 */
	@Override
	public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		if (entity.getAttackTarget() != null) {
			this.bipedRightArm.rotateAngleX += Math.PI;
			this.bipedLeftArm.rotateAngleX += Math.PI;
		}
	}
}
