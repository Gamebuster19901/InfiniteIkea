package com.gamebuster19901.scps.infiniteikea.client.renderer.entity.model;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.math.Vec3d;

public class ScalableRendererModel extends RendererModel {

	private Vec3d scale = new Vec3d(1,1,1);
	protected ScalableRendererModel parent;
	protected RendererModel vanilla;
	
	public ScalableRendererModel(Model model, RendererModel vanillaRendererModel) {
		super(model, vanillaRendererModel.textureOffsetX, vanillaRendererModel.textureOffsetY);
		this.vanilla = vanillaRendererModel;
	}
	
	@Override
	public void render(float vanillaScale) {
		GlStateManager.pushMatrix();
		Vec3d scale = getInverseParentalScaling();
		GlStateManager.scaled(scale.x, scale.y, scale.z);
		super.render(vanillaScale);
		GlStateManager.popMatrix();
	}
	
	public void setScale(float scaleX, float scaleY, float scaleZ) {
		assert scaleX > 0;
		assert scaleY > 0;
		assert scaleZ > 0;
		this.scale = (new Vec3d(scaleX, scaleY, scaleZ));
	}
	
	@Override
	@Deprecated
	public void addChild(RendererModel rendererModel) {
		super.addChild(rendererModel);
	}
	
	public void addChild(ScalableRendererModel child) {
		child.setParent(this);
		super.addChild(child);
		assert child.parent == this;
	}
	
	protected void setParent(ScalableRendererModel parent) {
		this.parent = parent;
	}
	
	public void setScale(Vec3d scale) {
		setScale((float)scale.x, (float)scale.y, (float)scale.z);
	}
	
	public Vec3d getScale() {
		return this.scale;
	}
	
	public void updateRotation() {
		this.rotateAngleX = vanilla.rotateAngleX;
		this.rotateAngleY = vanilla.rotateAngleY;
		this.rotationPointZ = vanilla.rotateAngleZ;
	}
	
	public Vec3d getInverseScale() {
		//System.out.println(1d / scale.x + ", " + 1d / scale.y + ", " + 1d / scale.z);
		return new Vec3d(1d / scale.x, 1d / scale.y, 1d / scale.z);
	}
	
	protected Vec3d getInverseParentalScaling() {
		if(this.parent == null) {
			return getScale();
		}
		
		byte loops = 0;
		Vec3d scale = this.getScale();
		ScalableRendererModel model = this;
		while(model.parent != null) {
			Vec3d localScale;
			model = model.parent;
			localScale = model.getInverseScale();
			if(loops < 0) {
				throw new Error("Parental hierarchy count greater than " + Byte.MAX_VALUE);
			}
			scale = scale.mul(localScale);
			loops++;
		}
		
		return scale;
	}
	
}
