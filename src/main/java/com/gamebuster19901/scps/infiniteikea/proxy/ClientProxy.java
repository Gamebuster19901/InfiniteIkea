package com.gamebuster19901.scps.infiniteikea.proxy;

import com.gamebuster19901.scps.infiniteikea.Main;
import com.gamebuster19901.scps.infiniteikea.client.renderer.entity.StaffRendererFactory;
import com.gamebuster19901.scps.infiniteikea.entity.Staff;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends Proxy {
	
	public ClientProxy() {
		super();
		addListener(this::clientSetup);
	}
	
	@SuppressWarnings("unused")
	private void clientSetup(FMLClientSetupEvent e) {
		Main.LOGGER.info("CLIENT SETUP");
		RenderingRegistry.registerEntityRenderingHandler(Staff.class, StaffRendererFactory.INSTANCE);
	}
	
}
