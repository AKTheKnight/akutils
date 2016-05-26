package com.aktheknight.akutils.blocks.levitators;

import com.aktheknight.akutils.AKUtils;
import com.aktheknight.akutils.blocks.tileentities.TELevitator;
import com.aktheknight.akutils.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        super(Material.ROCK);
        this.setUnlocalizedName("levitator");

        this.setHardness(2F);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(AKUtils.akUtilsTab);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TELevitator();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        ItemStack stack = new ItemStack(ModBlocks.levitator);
        NBTTagCompound tag = new NBTTagCompound();
        TELevitator lev = (TELevitator) world.getTileEntity(pos);
        if (lev != null) {
            if (lev.mobLev) {
                tag.setBoolean("mobLev", true);
            }
            if (lev.playerLev) {
                tag.setBoolean("playerLev", true);
            }
            if (lev.itemLev) {
                tag.setBoolean("itemLev", true);
            }
        }
        else {
            System.out.println("Uh oh. Null tile?");
        }
        stack.setTagCompound(tag);

        Random rand = new Random();

        float dX = rand.nextFloat() * 0.8F + 0.1F;
        float dY = rand.nextFloat() * 0.8F + 0.1F;
        float dZ = rand.nextFloat() * 0.8F + 0.1F;

        EntityItem entityItem = new EntityItem(world, pos.getX() + dX, pos.getY() + dY, pos.getZ() + dZ,
                stack.copy());

        if (stack.hasTagCompound())
        {
            entityItem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
        }

        float factor = 0.05F;
        entityItem.motionX = rand.nextGaussian() * factor;
        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
        entityItem.motionZ = rand.nextGaussian() * factor;
        world.spawnEntityInWorld(entityItem);
        stack.stackSize = 0;

        super.breakBlock(world, pos, state);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return new ArrayList<>();
    }
}
