package com.aktheknight.akutils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid= AKUtils.MODID, name= AKUtils.MODNAME, version= AKUtils.VERSION)

public class AKUtils {
	public static final String MODID = "akutils";
	static final String MODNAME = "AKUtils";
	static final String VERSION = "@VERSION@";
	
	@Mod.Instance
	public static AKUtils instance;
	
	static Logger LOGGER = LogManager.getLogger(MODID);
	
	@SidedProxy(clientSide="com.aktheknight.akutils.ClientProxy", serverSide="com.aktheknight.akutils.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER.log(Level.INFO, "Starting Pre-Init");
		proxy.preInit(event);
		LOGGER.log(Level.INFO, "Finished Pre-Init");
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.log(Level.INFO, "Starting Init");
	    proxy.init(event);
	    LOGGER.log(Level.INFO, "Finished Init");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOGGER.log(Level.INFO, "Starting Post Init");
	    proxy.postInit(event);
	    LOGGER.log(Level.INFO, "Finished Post Init");
	}

}
