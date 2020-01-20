package com.gamebuster19901.scps.infiniteikea.client.renderer.entity;

import com.gamebuster19901.scps.infiniteikea.Main;
import com.gamebuster19901.scps.infiniteikea.client.renderer.entity.model.StaffModel;
import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class StaffRenderer extends BipedRenderer<Staff, StaffModel>{

	private static final ResourceLocation STAFF_TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/staff/staff.png");
	
	public StaffRenderer(EntityRendererManager renderManagerIn, StaffModel modelBipedIn, float shadowSize) {
		super(renderManagerIn, modelBipedIn, shadowSize);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Staff entity) {
		return STAFF_TEXTURE;
	}

}
