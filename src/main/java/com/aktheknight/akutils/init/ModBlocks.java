package com.aktheknight.akutils.init;

import com.aktheknight.akutils.AKUtils;
import com.aktheknight.akutils.blocks.GrowthAccelerator;
import com.aktheknight.akutils.blocks.levitators.LevitatorBase;
import com.aktheknight.akutils.blocks.SuperGrowthAccelerator;
import com.aktheknight.akutils.blocks.levitators.LevitatorItems;
import com.aktheknight.akutils.blocks.levitators.LevitatorMobs;
import com.aktheknight.akutils.blocks.levitators.LevitatorPlayer;
import com.aktheknight.akutils.blocks.tileentities.TELevitatorBase;
import com.aktheknight.akutils.blocks.tileentities.TELevitatorItems;
import com.aktheknight.akutils.blocks.tileentities.TELevitatorMobs;
import com.aktheknight.akutils.blocks.tileentities.TELevitatorPlayer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

	//public static Block shrinkOre;

    public static Block growthAccelerator;
    public static Block superGrowthAccelerator;

    public static Block levitator;
    
    public static Block levitatorPlayer;
    public static Block levitatorItems;
    public static Block levitatorMobs;

    public static void init() {
    	//GameRegistry.registerBlock(shrinkOre = new ShrinkOre(Material.rock, ModItems.shrinkGem), "shrinkOre");

        growthAccelerator = new GrowthAccelerator();
        registerBlock(growthAccelerator, "growthaccelerator", true);

        superGrowthAccelerator = new SuperGrowthAccelerator();
        registerBlock(superGrowthAccelerator, "supergrowthaccelerator", true);

        levitator = new LevitatorBase();
        registerTile(levitator, "levitator", TELevitatorBase.class, true);
        
        levitatorPlayer = new LevitatorPlayer();
        registerTile(levitatorPlayer, "levitatorplayer", TELevitatorPlayer.class, true);

        levitatorItems = new LevitatorItems();
        registerTile(levitatorItems, "levitatoritems", TELevitatorItems.class, true);

        levitatorMobs = new LevitatorMobs();
        registerTile(levitatorMobs, "levitatormobs", TELevitatorMobs.class, true);
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
