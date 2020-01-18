package com.gamebuster19901.scps.infiniteikea.network;

import com.gamebuster19901.scps.infiniteikea.Main;
import com.gamebuster19901.scps.infiniteikea.network.packet.server.PlayLightSound;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Network {

	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(Main.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	
	private Network() {
		throw new AssertionError();
	}
	
	public static void Register() {
		int id = 0;
		PlayLightSound.Register(id++);
	}
	
}
