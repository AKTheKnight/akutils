package com.aktheknight.akutils.blocks;

import com.aktheknight.akutils.ConfigHandler;
import com.aktheknight.akutils.util.FixedRandom;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.List;
import java.util.Random;

public class SuperGrowthAccelerator extends GrowthAccelerator {
    /**
     * sms
     * mgm
     * sms
     * s: stone
     * g: growth accel
     * m: super meal
     */

    private FixedRandom fixedRandom;

    public SuperGrowthAccelerator() {
        super();

        this.setUnlocalizedName("supergrowthaccelerator");
    }

    @Override
    void getRadius() {
        this.radius = ConfigHandler.SuperGrowthAccRadius;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.AQUA + "Makes things grow \"super\" fast ;)");
        tooltip.add(TextFormatting.GOLD + "Affects an area of crops");
    }
}
