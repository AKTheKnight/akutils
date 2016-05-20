package com.aktheknight.akutils.init;

import com.aktheknight.akutils.AKUtils;
import com.aktheknight.akutils.items.DirtyHoe;
import com.aktheknight.akutils.items.SuperMeal;
import com.aktheknight.akutils.items.TestingStick;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by alex_ on 25/03/2016.
 */
public class ModItems {
	
	public static Item testingStick;

	public static Item superMeal;
    public static Item dirtyHoe;

	public static void init() {
		
		testingStick = new TestingStick();
		registerItem(testingStick, "testingstick");

        superMeal = new SuperMeal();
        registerItem(superMeal, "supermeal");

        dirtyHoe = new DirtyHoe();
        registerItem(dirtyHoe, "dirtyhoe");
	}

	static void registerItem(Item item, String name) {
		GameRegistry.register(item, new ResourceLocation(AKUtils.MODID + ":" + name));
	}
}
