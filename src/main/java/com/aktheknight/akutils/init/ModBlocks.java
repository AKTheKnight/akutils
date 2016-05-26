package com.aktheknight.akutils.init;

import com.aktheknight.akutils.AKUtils;
import com.aktheknight.akutils.blocks.GrowthAccelerator;
import com.aktheknight.akutils.blocks.SuperGrowthAccelerator;
import com.aktheknight.akutils.blocks.itemblocks.LevitatorItemBlock;
import com.aktheknight.akutils.blocks.levitators.Levitator;
import com.aktheknight.akutils.blocks.tileentities.TELevitator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

	//public static Block shrinkOre;

    public static Block growthAccelerator;
    public static Block superGrowthAccelerator;

    public static Block levitator;

    public static void init() {
    	//GameRegistry.registerBlock(shrinkOre = new ShrinkOre(Material.rock, ModItems.shrinkGem), "shrinkOre");

        growthAccelerator = new GrowthAccelerator();
        registerBlock(growthAccelerator, "growthaccelerator", true);

        superGrowthAccelerator = new SuperGrowthAccelerator();
        registerBlock(superGrowthAccelerator, "supergrowthaccelerator", true);

        levitator = new Levitator();
        registerTile(levitator, "levitator", TELevitator.class, false);
        GameRegistry.register(new LevitatorItemBlock(levitator), new ResourceLocation(AKUtils.MODID + ":" + "levitator"));
    }

    static void registerBlock(Block block, String name, boolean itemblock) {
        GameRegistry.register(block, new ResourceLocation(AKUtils.MODID + ":" + name));
        if (itemblock) {
            GameRegistry.register(new ItemBlock(block), new ResourceLocation(AKUtils.MODID + ":" + name));
        }
    }

    static void registerTile(Block block, String name, Class clazz, boolean itemblock) {
        registerBlock(block, name, itemblock);
        GameRegistry.registerTileEntity(clazz, name);
    }

}
