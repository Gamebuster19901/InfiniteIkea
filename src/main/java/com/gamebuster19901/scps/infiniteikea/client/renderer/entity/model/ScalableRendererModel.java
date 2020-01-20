package com.gamebuster19901.scps.infiniteikea.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.math.Vec3d;

public class ScalableRendererModel extends RendererModel{

	public float scaleX = 1;
	public float scaleY = 1;
	public float scaleZ = 1;
	
	public ScalableRendererModel(Model model, String boxNameIn) {
		super(model, boxNameIn);
	}

	public ScalableRendererModel(Model model) {
		super(model);
	}

	public ScalableRendererModel(Model model, int texOffX, int texOffY) {
		super(model, texOffX, texOffY);
	}
	
	public void setScale(float scaleX, float scaleY, float scaleZ) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
	}
	
	public void setScale(Vec3d scale) {
		setScale((float)scale.x, (float)scale.y, (float)scale.z);
	}
	
	public Vec3d getScale() {
		return new Vec3d(scaleX, scaleY, scaleZ);
	}
	
}