package com.aktheknight.akutils.items;

import com.aktheknight.akutils.ConfigHandler;
import net.minecraft.block.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class DirtyHoe extends Item {

    public DirtyHoe() {
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setUnlocalizedName("dirtyhoe");
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && world.getBlockState(pos).getBlock() instanceof BlockGrass) {
            BlockPos nextPos;
            Block nextBlock;
            int radius = ConfigHandler.DirtyHoeRadius;
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        nextPos = pos.add(x, y, z);
                        nextBlock = world.getBlockState(nextPos).getBlock();
                        if (nextBlock instanceof BlockGrass
                                && !(world.getBlockState(nextPos.up()).getBlock() instanceof IGrowable)
                                && !world.isAirBlock(nextPos.up()))
                            world.setBlockState(nextPos, Blocks.dirt.getDefaultState());
                        else if (nextBlock instanceof BlockFarmland
                                && world.isAirBlock(nextPos.up()))
                            world.setBlockState(nextPos, Blocks.grass.getDefaultState());
                        else if (nextBlock instanceof BlockDirt
                                && (world.isAirBlock(nextPos.up())  || world.getBlockState(nextPos.up()).getBlock() instanceof IGrowable))
                            world.setBlockState(nextPos, Blocks.grass.getDefaultState());
                    }
                }
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.AQUA + "No, not your mum");
        tooltip.add(TextFormatting.GOLD + "Turns dirt into grass (right click on grass");
    }
}
