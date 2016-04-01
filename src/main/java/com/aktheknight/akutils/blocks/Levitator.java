package com.aktheknight.akutils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by alex_ on 31/03/2016.
 */
public class Levitator extends Block implements ITileEntityProvider {

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
}
