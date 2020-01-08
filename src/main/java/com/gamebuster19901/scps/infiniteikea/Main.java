package com.gamebuster19901.scps.infiniteikea;

import static com.gamebuster19901.scps.infiniteikea.Main.MODID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gamebuster19901.scps.infiniteikea.proxy.*;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;


@Mod(value = MODID)
public class Main {
	public static final String MODID = "infiniteikea";
	public static final String MODNAME = "Infinite Ikea (SCP-3008)";
	public static final String VERSION = "0.0.0.0-1.14.4";
	public static final Logger LOGGER = LogManager.getLogger(MODNAME);
	
	public static Proxy proxy;
	
	private static Main instance;
	
	{
		proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	}
	
	public static Main getInstance(){
		return instance;
	}
}
