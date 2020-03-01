package com.gamebuster19901.scps.infiniteikea.client.renderer.entity.model;

import static com.gamebuster19901.scps.infiniteikea.entity.Staff.*;

import com.gamebuster19901.scps.infiniteikea.entity.Staff;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.common.MinecraftForge;

public class StaffModel extends BipedModel<Staff> {
	
	private boolean defined = false;
	
	@Override
	public void render(Staff staff, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float vanillaScale) {
		if(!defined) {
			defineModel(staff);
		}
		GlStateManager.pushMatrix();
		if (staff.shouldRenderSneaking()) {
			GlStateManager.translatef(0.0F, 0.2F, 0.0F);
		}
		bipedBody.render(vanillaScale);
		GlStateManager.popMatrix();
	}
	
	public StaffModel() {
		MinecraftForge.EVENT_BUS.register(this);
		textureHeight = 64;
		textureWidth = 64;
	}
	
	private void defineModel(Staff staff) {
		defineModel(staff.getParam(HEAD_WIDTH), staff.getParam(HEAD_HEIGHT), staff.getParam(BODY_WIDTH), staff.getParam(BODY_HEIGHT), staff.getParam(ARM_WIDTH), staff.getParam(ARM_LENGTH), staff.getParam(LEG_LENGTH), staff.getParam(LEG_WIDTH));
	}
	
	private void defineModel(float headWidthScale, float headHeightScale, float bodyWidthScale, float bodyHeightScale, float armWidthScale, float armLengthScale, float legLengthScale, float legWidthScale) {
		ScalableRendererModel bipedHead = new ScalableRendererModel(this, this.bipedHead);
		ScalableRendererModel bipedHeadwear = new ScalableRendererModel(this, this.bipedHeadwear);
		ScalableRendererModel bipedBody = new ScalableRendererModel(this, this.bipedBody);
		ScalableRendererModel bipedLeftArm = new ScalableRendererModel(this, this.bipedLeftArm);
		ScalableRendererModel bipedRightArm = new ScalableRendererModel(this, this.bipedRightArm);
		ScalableRendererModel bipedLeftLeg = new ScalableRendererModel(this, this.bipedLeftLeg);
		ScalableRendererModel bipedRightLeg = new ScalableRendererModel(this, this.bipedRightLeg);
		
		
		this.bipedHead = bipedHead;
		this.bipedHeadwear = bipedHeadwear;
		this.bipedBody = bipedBody;
		this.bipedLeftArm = bipedLeftArm;
		this.bipedRightArm = bipedRightArm;
		this.bipedLeftLeg = bipedLeftLeg;
		this.bipedRightLeg = bipedRightLeg;
		
		bipedHead.updateRotation();
		bipedHeadwear.updateRotation();
		bipedBody.updateRotation();
		bipedLeftArm.updateRotation();
		bipedRightArm.updateRotation();
		bipedLeftLeg.updateRotation();
		bipedRightLeg.updateRotation();
		
		int longLength = 12;
		int armWidth = 4;
		
		//HEAD
		bipedHead.setScale(headWidthScale, headHeightScale, headWidthScale);
		bipedHead.addBox(-4f, -8, -4f, 8, 8, 8);
		bipedHead.setRotationPoint(0f, 0,0);
		
		//HEADWEAR
		bipedHeadwear.setScale(headWidthScale, headHeightScale, headWidthScale);
		bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5f);
		bipedHeadwear.setRotationPoint(0, 0, 0);

		//BODY
		bipedBody.setScale(bodyWidthScale, bodyHeightScale, bodyWidthScale);
		bipedBody.addBox(-4f, 0, -2f, 8, 12, 4);
		bipedBody.setRotationPoint(0f, 0, 0f);
		
		//LEFT ARM
		bipedLeftArm.setScale(armWidthScale, armLengthScale, armWidthScale);
		bipedLeftArm.addBox(0, 0, -2f, 4, 12, 4);
		bipedLeftArm.setRotationPoint(armWidth / armWidthScale * bodyWidthScale, 0, 0f);
		
		//RIGHT ARM
		bipedRightArm.setScale(armWidthScale, armLengthScale, armWidthScale);
		bipedRightArm.addBox(-4, 0, -2.0F, 4, 12, 4);
		bipedRightArm.setRotationPoint(-(armWidth / armWidthScale) * bodyWidthScale, 0f, 0f);
		
		//LEFT LEG

		bipedLeftLeg.mirror = true;
		bipedLeftLeg.setScale(legWidthScale, legLengthScale, legWidthScale);
		bipedLeftLeg.addBox(-2f, 0, -2f, 4, 12, 4);
		bipedLeftLeg.setRotationPoint(2f, longLength - getHyperbolicResult(longLength, legLengthScale), 0);
		
		//RIGHT LEG
		bipedRightLeg.setScale(legWidthScale, legLengthScale, legWidthScale);
		bipedRightLeg.addBox(0, 0, -2f, 4, 12, 4);
		bipedRightLeg.setRotationPoint(-4f, longLength - getHyperbolicResult(longLength, legLengthScale), 0f);
		
		//ADD CHILDREN
		bipedBody.addChild(bipedHead);
		bipedBody.addChild(bipedHeadwear);
		bipedBody.addChild(bipedLeftArm);
		bipedBody.addChild(bipedRightArm);
		bipedBody.addChild(bipedLeftLeg);
		bipedBody.addChild(bipedRightLeg);
		
	}
	
	protected final float getHyperbolicResult(float defaultLength, float scale) {
		return defaultLength -(defaultLength / scale);
	}
}
