package com.aktheknight.akutils.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesDyes;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init() {
		shapeless();
		shaped();
	}
	
	private static void shapeless() {
		
	}
	
	private static void shaped() {
		//Items
		//GameRegistry.addRecipe(new ItemStack(ModItems.shrinkGem), "##", "##", '#', ModItems.shrinkShard);
		//SuperMeal
		GameRegistry.addRecipe(
				new ItemStack(ModItems.superMeal, 3),
				"blb",
				"bgb",
				"blb",
				'b', new ItemStack(Items.dye, 1, EnumDyeColor.WHITE.getDyeDamage()),
				'l', new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeDamage()),
				'g', Items.gold_ingot
		);

		//GrowthAcclerator
        GameRegistry.addRecipe(
                new ItemStack(ModBlocks.growthAccelerator),
                "srs",
                "rpr",
                "brb",
                's', Items.wheat_seeds,
                'r', Items.redstone,
                'p', Blocks.piston,
                'b', new ItemStack(Blocks.stone, 1, 0)
        );

        //SuperGrowthAcclerator
        GameRegistry.addRecipe(
                new ItemStack(ModBlocks.superGrowthAccelerator),
                "sms",
                "mgm",
                "sms",
                's', new ItemStack(Blocks.stone, 1, 0),
                'g', ModBlocks.growthAccelerator,
                'm', ModItems.superMeal
        );

        //Dirty Hoe left
        GameRegistry.addRecipe(
                new ItemStack(ModItems.dirtyHoe),
                "gg ",
                " s ",
                " s ",
                'g', ModItems.superMeal,
                's', Items.stick
        );

        //Dirty Hoe right
        GameRegistry.addRecipe(
                new ItemStack(ModItems.dirtyHoe),
                " gg",
                " s ",
                " s ",
                'g', ModItems.superMeal,
                's', Items.stick
        );
	}
}
