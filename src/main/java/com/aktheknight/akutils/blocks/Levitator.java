package com.aktheknight.akutils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by alex_ on 31/03/2016.
 */
public class Levitator extends Block implements ITileEntityProvider {
    /**
     * sls
     * sps
     * ses
     * s stone
     * l slime block
     * p piston
     * e enderpearl
     */

    public Levitator() {
        super(Material.rock);
        this.setUnlocalizedName("levitator");

        this.setHardness(2F);
        this.setStepSound(SoundType.STONE);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityLevitator();
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.AQUA + "Makes things go up ;)");
        tooltip.add(TextFormatting.GOLD + "Shift to go down");
    }
}
