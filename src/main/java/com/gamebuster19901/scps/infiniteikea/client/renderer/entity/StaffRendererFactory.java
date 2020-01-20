package com.gamebuster19901.scps.infiniteikea.client.renderer.entity;

import com.gamebuster19901.scps.infiniteikea.client.renderer.entity.model.StaffModel;
import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class StaffRendererFactory implements IRenderFactory<Staff>{

	public static final StaffRendererFactory INSTANCE = new StaffRendererFactory();
	
	private StaffRendererFactory() {
		if (INSTANCE == null) {
			return;
		}
		throw new AssertionError();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EntityRenderer<? super Staff> createRenderFor(EntityRendererManager manager) {
		return new StaffRenderer(manager, new StaffModel(8,4,4,8,0,0,0), 1f);
	}

}
