package com.gamebuster19901.scps.infiniteikea.client.renderer.entity.model;

import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StaffModel extends BipedModel<Staff>{

	@SuppressWarnings("unchecked")
	public StaffModel(int headWidth, int headHeight, int bodyWidth, int bodyHeight, int armWidth, int armLength, int legLength) {
		MinecraftForge.EVENT_BUS.register(this);
		this.textureHeight = 64;
		this.textureWidth = 64;
		/*
		//HEAD
		this.bipedHead = new RendererModel(this, 0, 0);
		this.bipedHead.addBox(-4f, -8f, -4f, headWidth, headHeight, 8);
		this.bipedHead.setRotationPoint(0f, 0f, 0f);
		
		//BODY
		this.bipedBody = new RendererModel(this, 16, 16);
		this.bipedBody.addBox(-4f, 0f, -2f, bodyWidth, bodyWidth, 4);
		this.bipedBody.setRotationPoint(0f, 0f, 0f);
		
		//LEFT ARM
		this.bipedLeftArm = new RendererModel(this, 32, 48);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1f, -2f, -2f, armWidth, armLength, 4);
		this.bipedLeftArm.setRotationPoint(-5f, 2f, 0f);
		
		//RIGHT ARM
		this.bipedRightArm = new RendererModel(this, 40, 16);
		this.bipedRightArm.addBox(-3f, -2f, -2f, armWidth, armLength, 4);
		this.bipedRightArm.setRotationPoint(-5f, -2f, 0f);
		
		//LEFT LEG
		this.bipedLeftLeg = new RendererModel(this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-2f, 0f, -2f, 4, legLength, 4);
		this.bipedLeftLeg.setRotationPoint(1.9f, 12f, 0);
		
		//RIGHT LEG
		this.bipedRightLeg = new RendererModel(this, 0, 16);
		this.bipedRightLeg.addBox(-2f, 0f, -2f, 4, legLength, 4);
		this.bipedRightLeg.setRotationPoint(-1.9f, 12f, 0f);
		*/
	}
	
	@SubscribeEvent
	public void onClientTick(ClientTickEvent e) {
		testModel(8,8,8,12,4,12,5);
	}
	
	public void testModel(int headWidth, int headHeight, int bodyWidth, int bodyHeight, int armWidth, int armLength, int legLength) {
		this.bipedHead = new RendererModel(this, 0, 0);
		this.bipedHead.addBox(-4f, -8f, -4f, headWidth, headHeight, 8);
		this.bipedHead.setRotationPoint(0f, 0f, 0f);

		//BODY
		this.bipedBody = new RendererModel(this, 16, 16);
		this.bipedBody.addBox(-4f, 0f, -2f, bodyWidth, bodyHeight, 4);
		this.bipedBody.setRotationPoint(0f, 0f, 0f);
		
		//LEFT ARM
		this.bipedLeftArm = new RendererModel(this, 32, 48);
		this.bipedLeftArm.addBox(-1f, -2f, -2f, armWidth, armLength, 4);
		this.bipedLeftArm.setRotationPoint(5f, 2f, 0f);
		
		//RIGHT ARM
		this.bipedRightArm = new RendererModel(this, 40, 16);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, armWidth, 12, 4);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		
		//LEFT LEG
		this.bipedLeftLeg = new RendererModel(this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-2f, 12 - legLength, -2f, 4, legLength, 4);
		this.bipedLeftLeg.setRotationPoint(1.9f, -12f, 0);
		
		//RIGHT LEG
		this.bipedRightLeg = new RendererModel(this, 0, 16);
		this.bipedRightLeg.addBox(-2f, 12 - legLength, -2f, 4, legLength, 4);
		this.bipedRightLeg.setRotationPoint(-1.9f, -12 - legLength, 0f);
		
		this.bipedHeadwear = new RendererModel(this, 0, 0);
		this.bipedHeadwear.addBox(0,0,0,0,0,0);
	}
}
