package com.aktheknight.akutils.init;

import com.aktheknight.akutils.ConfigHandler;
import com.aktheknight.akutils.recipes.LevitatorRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
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
				new ItemStack(ModItems.superMeal, ConfigHandler.SuperMealOutputAmount),
				"blb",
				"bgb",
				"blb",
				'b', new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage()),
				'l', new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()),
				'g', Items.GOLD_INGOT
		);

		//GrowthAcclerator
        GameRegistry.addRecipe(
                new ItemStack(ModBlocks.growthAccelerator),
                "srs",
                "rpr",
                "brb",
                's', Items.WHEAT_SEEDS,
                'r', Items.REDSTONE,
                'p', Blocks.PISTON,
                'b', new ItemStack(Blocks.STONE, 1, 0)
        );

        //SuperGrowthAcclerator
        GameRegistry.addRecipe(
                new ItemStack(ModBlocks.superGrowthAccelerator),
                "sms",
                "mgm",
                "sms",
                's', new ItemStack(Blocks.STONE, 1, 0),
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
                's', Items.STICK
        );

        //Dirty Hoe right
        GameRegistry.addRecipe(
                new ItemStack(ModItems.dirtyHoe),
                " gg",
                " s ",
                " s ",
                'g', ModItems.superMeal,
                's', Items.STICK
        );

		//Levitator
		GameRegistry.addRecipe(
                new ItemStack(ModBlocks.levitator),
                "sls",
                "sps",
                "ses",
                's', new ItemStack(Blocks.STONE, 1, 0),
                'l', Blocks.SLIME_BLOCK,
                'p', Blocks.PISTON,
                'e', Items.ENDER_PEARL
        );

        GameRegistry.addRecipe(new LevitatorRecipe(Items.PAPER));

        GameRegistry.addRecipe(new LevitatorRecipe(Items.BONE));
        GameRegistry.addRecipe(new LevitatorRecipe(Items.ARMOR_STAND));
        GameRegistry.addRecipe(new LevitatorRecipe(Items.ITEM_FRAME));
		GameRegistry.addRecipe(new LevitatorRecipe(Items.REDSTONE));
	}
}
