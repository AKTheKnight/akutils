package com.aktheknight.akutils.blocks;

import com.aktheknight.akutils.ConfigHandler;
import com.aktheknight.akutils.util.FixedRandom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

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
