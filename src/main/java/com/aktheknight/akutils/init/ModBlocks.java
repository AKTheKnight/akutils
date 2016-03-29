package com.aktheknight.akutils.init;

import com.aktheknight.akutils.blocks.GrowthAccelerator;
import com.aktheknight.akutils.blocks.SuperGrowthAccelerator;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

	//public static Block shrinkOre;

    public static Block growthAccelerator;
    public static Block superGrowthAccelerator;

    public static void init() {
    	//GameRegistry.registerBlock(shrinkOre = new ShrinkOre(Material.rock, ModItems.shrinkGem), "shrinkOre");

        growthAccelerator = new GrowthAccelerator();
        GameRegistry.registerBlock(growthAccelerator, "growthaccelerator");

        superGrowthAccelerator = new SuperGrowthAccelerator();
        GameRegistry.registerBlock(superGrowthAccelerator, "supergrowthaccelerator");
    }

}
