package com.aktheknight.akutils.init;

import com.aktheknight.akutils.items.DirtyHoe;
import com.aktheknight.akutils.items.SuperMeal;
import com.aktheknight.akutils.items.TestingStick;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
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
		GameRegistry.registerItem(testingStick, "testingstick");

        superMeal = new SuperMeal();
        GameRegistry.registerItem(superMeal, "supermeal");

        dirtyHoe = new DirtyHoe();
        GameRegistry.registerItem(dirtyHoe, "dirtyhoe");
	}
}
