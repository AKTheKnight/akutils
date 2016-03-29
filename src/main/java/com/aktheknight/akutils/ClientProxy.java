package com.aktheknight.akutils;

import com.aktheknight.akutils.init.RenderInv;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		LOGGER.log(Level.INFO, "Setting up item and block rendering");
		RenderInv.init();
		LOGGER.log(Level.INFO, "Finished setting up item and block rendering");
	}
	
}
