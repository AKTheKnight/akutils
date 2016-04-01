package com.aktheknight.akutils.init;

import com.aktheknight.akutils.AKUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class RenderInv {
	
	public static void init() {
		
//		InventoryBlockRender(ModBlocks.demoBlock, "demoBlock");
        InventoryBlockRender(ModBlocks.growthAccelerator, "growthaccelerator");
        InventoryBlockRender(ModBlocks.superGrowthAccelerator, "supergrowthaccelerator");

        InventoryBlockRender(ModBlocks.levitator, "levitator");
		
//		InventoryItemRender(ModItems.demoItem, "demoItem");
        InventoryItemRender(ModItems.testingStick, "testingstick");
        InventoryItemRender(ModItems.superMeal, "supermeal");
        InventoryItemRender(ModItems.dirtyHoe, "dirtyhoe");
    }

    public static void InventoryBlockRender(Block block, String blockName) {

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(AKUtils.MODID + ":" + blockName, "inventory"));
    }

    public static void InventoryItemRender(Item item, String itemName) {

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(AKUtils.MODID + ":" + itemName, "inventory"));
    }
}
