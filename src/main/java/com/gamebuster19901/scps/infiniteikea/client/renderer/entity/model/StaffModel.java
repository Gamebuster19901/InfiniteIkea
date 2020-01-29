package com.gamebuster19901.scps.infiniteikea.client.renderer.entity.model;

import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StaffModel extends BipedModel<Staff>{

	@SuppressWarnings("unchecked")
	public StaffModel(float headWidthScale, float headHeightScale, float bodyWidthScale, float bodyHeightScale, float armWidthScale, float armLengthScale, float legLengthScale) {
		MinecraftForge.EVENT_BUS.register(this);
		textureHeight = 64;
		textureWidth = 64;
		/*
		//HEAD
		bipedHead = new RendererModel(this, 0, 0);
		bipedHead.addBox(-4f, -8f, -4f, headWidth, headHeight, 8);
		bipedHead.setDefaultRotationPoint(0f, 0f, 0f);
		
		//BODY
		bipedBody = new RendererModel(this, 16, 16);
		bipedBody.addBox(-4f, 0f, -2f, bodyWidth, bodyWidth, 4);
		bipedBody.setDefaultRotationPoint(0f, 0f, 0f);
		
		//LEFT ARM
		bipedLeftArm = new RendererModel(this, 32, 48);
		bipedLeftArm.mirror = true;
		bipedLeftArm.addBox(-1f, -2f, -2f, armWidth, armLength, 4);
		bipedLeftArm.setDefaultRotationPoint(-5f, 2f, 0f);
		
		//RIGHT ARM
		bipedRightArm = new RendererModel(this, 40, 16);
		bipedRightArm.addBox(-3f, -2f, -2f, armWidth, armLength, 4);
		bipedRightArm.setDefaultRotationPoint(-5f, -2f, 0f);
		
		//LEFT LEG
		bipedLeftLeg = new RendererModel(this, 0, 16);
		bipedLeftLeg.mirror = true;
		bipedLeftLeg.addBox(-2f, 0f, -2f, 4, legLength, 4);
		bipedLeftLeg.setDefaultRotationPoint(1.9f, 12f, 0);
		
		//RIGHT LEG
		bipedRightLeg = new RendererModel(this, 0, 16);
		bipedRightLeg.addBox(-2f, 0f, -2f, 4, legLength, 4);
		bipedRightLeg.setDefaultRotationPoint(-1.9f, 12f, 0f);
		*/
	}
	
	@SubscribeEvent
	public void onClientTick(ClientTickEvent e) {
		testModel(1,1f,1,1f,1,1,1f);
	}
	
	public void testModel(float headWidthScale, float headHeightScale, float bodyWidthScale, float bodyHeightScale, float armWidthScale, float armLengthScale, float legLengthScale) {
		ScalableRendererModel bipedHead = new ScalableRendererModel(this, 0, 0);
		ScalableRendererModel bipedHeadwear = new ScalableRendererModel(this, 32, 0);
		ScalableRendererModel bipedBody = new ScalableRendererModel(this, 16, 16);
		ScalableRendererModel bipedLeftArm = new ScalableRendererModel(this, 32, 48);
		ScalableRendererModel bipedRightArm = new ScalableRendererModel(this, 40, 16);
		ScalableRendererModel bipedLeftLeg = new ScalableRendererModel(this, 0, 16);
		ScalableRendererModel bipedRightLeg = new ScalableRendererModel(this, 0, 16);
		
		this.bipedHead = bipedHead;
		this.bipedHeadwear = bipedHeadwear;
		this.bipedBody = bipedBody;
		this.bipedLeftArm = bipedLeftArm;
		this.bipedRightArm = bipedRightArm;
		this.bipedLeftLeg = bipedLeftLeg;
		this.bipedRightLeg = bipedRightLeg;
		
		int longLength = 12;
		int armWidth = 4;
		int head = 8;
		
		//HEAD
		bipedHead.setScale(headWidthScale, headHeightScale, 1f);
		bipedHead.addBox(-4f, 0, -4f, 8, 8, 8);
		bipedHead.setDefaultRotationPoint(0f, 0,0);
		
		//HEADWEAR
		//-getHyperbolicResult(longLength, bodyHeightScale) * bodyHeightScale, 0)
		//bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5f);
		//bipedHeadwear.setDefaultRotationPoint(0, 0, 0);

		//BODY
		bipedBody.setScale(bodyWidthScale, bodyHeightScale, 1);
		bipedBody.addBox(-4f, 0, -2f, 8, 12, 4);
		bipedBody.setDefaultRotationPoint(0f, -getHyperbolicResult(longLength, bodyHeightScale), 0f);
		
		//LEFT ARM
		bipedLeftArm.setScale(1, armLengthScale, 1);
		bipedLeftArm.addBox(-1f, -2f, -2f, 4, 12, 4);
		bipedLeftArm.setDefaultRotationPoint(5f + getHyperbolicResult(8, bodyWidthScale), 2f - getHyperbolicResult(longLength, bodyHeightScale) * bodyHeightScale, 0f);
		
		//RIGHT ARM
		bipedRightArm.setScale(1, armLengthScale, 1);
		bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		bipedRightArm.setDefaultRotationPoint(-5.0F - getHyperbolicResult(8, bodyWidthScale), 2.0F - getHyperbolicResult(longLength, bodyHeightScale) * bodyHeightScale, 0.0F);
		
		//LEFT LEG

		bipedLeftLeg.mirror = true;
		bipedLeftLeg.setScale(1, legLengthScale, 1);
		bipedLeftLeg.addBox(-2f, 0, -2f, 4, 12, 4);
		bipedLeftLeg.setDefaultRotationPoint(1.9f, 12 - getHyperbolicResult(longLength, legLengthScale), 0);
		
		//RIGHT LEG
		bipedRightLeg.setScale(1, legLengthScale, 1);
		bipedRightLeg.addBox(-2f, 0, -2f, 4, 12, 4);
		bipedRightLeg.setDefaultRotationPoint(-1.9f, 12 - getHyperbolicResult(longLength, legLengthScale), 0f);
		
	}
	
	@Override
	public void setRotationAngles(Staff staff, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float vanillaScaleFactor) {
		super.setRotationAngles(staff, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, vanillaScaleFactor);
		if(this.bipedHead instanceof ScalableRendererModel) {
			((ScalableRendererModel) bipedHead).resetRotationPoint();
		}
		if(this.bipedHeadwear instanceof ScalableRendererModel) {
			((ScalableRendererModel) bipedHeadwear).resetRotationPoint();
		}
		if(this.bipedBody instanceof ScalableRendererModel) {
			((ScalableRendererModel) bipedBody).resetRotationPoint();
		}
		if(this.bipedLeftArm instanceof ScalableRendererModel) {
			((ScalableRendererModel) bipedLeftArm).resetRotationPoint();
		}
		if(this.bipedRightArm instanceof ScalableRendererModel) {
			((ScalableRendererModel) bipedRightArm).resetRotationPoint();
		}
		if(this.bipedLeftLeg instanceof ScalableRendererModel) {
			((ScalableRendererModel) bipedLeftLeg).resetRotationPoint();
		}
		if(this.bipedRightLeg instanceof ScalableRendererModel) {
			((ScalableRendererModel) bipedRightLeg).resetRotationPoint();
		}
	}
	
	protected final float getHyperbolicResult(float defaultLength, float scale) {
		return defaultLength -(defaultLength / scale);
	}
}
