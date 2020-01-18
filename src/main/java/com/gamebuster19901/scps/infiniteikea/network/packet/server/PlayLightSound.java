package com.gamebuster19901.scps.infiniteikea.network.packet.server;

import java.util.function.Supplier;

import com.gamebuster19901.scps.infiniteikea.client.audio.WorldLightSound;
import com.gamebuster19901.scps.infiniteikea.network.Network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayLightSound {

	public PlayLightSound() {};
	
	@SuppressWarnings("unused")
	public PlayLightSound(PacketBuffer buf) {};
	
	@SuppressWarnings("unused")
	public void encode(PacketBuffer buf) {};
	
	public void handle(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			Minecraft.getInstance().getSoundHandler().play(new WorldLightSound());
		});
	}
	
	public static void Register(int id) {
		Network.NETWORK.registerMessage(id, PlayLightSound.class, PlayLightSound::encode, PlayLightSound::new, PlayLightSound::handle);
	}
	
}
