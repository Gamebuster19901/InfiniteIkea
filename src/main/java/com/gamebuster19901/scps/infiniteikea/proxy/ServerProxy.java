package com.gamebuster19901.scps.infiniteikea.proxy;

import com.gamebuster19901.scps.infiniteikea.Main;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

public class ServerProxy extends Proxy {
	
	public ServerProxy() {
		super();
		addListener(this::serverSetup);
	}
	
	@SuppressWarnings("unused")
	private void serverSetup(FMLServerStartingEvent e) {
		Main.LOGGER.info("SERVER SETUP");
	}
}
