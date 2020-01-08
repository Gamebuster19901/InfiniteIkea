package com.gamebuster19901.scps.infiniteikea.proxy;

import java.util.function.Consumer;

import com.gamebuster19901.scps.infiniteikea.Main;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class Proxy{
	
	public Proxy() {
		addListener(this::setup);
	}

	@SuppressWarnings("unused")
	private void setup(FMLCommonSetupEvent event) {
		Main.LOGGER.info("COMMON SETUP");
	}

	protected static IEventBus getBus() {
		return FMLJavaModLoadingContext.get().getModEventBus();
	}
	
	protected static <T extends Event> void addListener(Consumer<T>consumer) {
		getBus().addListener(consumer);
	}
	
}
