package com.aktheknight.akutils.blocks.itemblocks;

import com.aktheknight.akutils.blocks.tileentities.TELevitator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 25/05/16.
 */
public class LevitatorItemBlock extends ItemBlock {

    public LevitatorItemBlock(Block block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
                                float hitX, float hitY, float hitZ, IBlockState newState) {
        if (!world.setBlockState(pos, newState)) {
            return false;
        }
        if (world.getBlockState(pos).getBlock() == block) {
            world.getBlockState(pos).getBlock().onBlockPlacedBy(world, pos, newState, player, stack);
        }
        if (stack != null && stack.hasTagCompound()) {
            TELevitator lev = (TELevitator) world.getTileEntity(pos);
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.hasKey("playerLev")
                    && tag.getBoolean("playerLev"))
                lev.playerLev = true;
            if (tag.hasKey("mobLev")
                    && tag.getBoolean("mobLev"))
                lev.mobLev = true;
            if (tag.hasKey("itemLev")
                    && tag.getBoolean("itemLev"))
                lev.itemLev = true;
            if (tag.hasKey("redstone")
                    && tag.getBoolean("redstone"))
                lev.redstone = true;
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        ArrayList<String> list = new ArrayList<>();
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null) {
            if (tag.hasKey("playerLev")
                    && tag.getBoolean("playerLev")) {
                list.add("Levitates players");
            }
            if (tag.hasKey("mobLev")
                    && tag.getBoolean("mobLev")) {
                list.add("Levitates mobs");
            }
            if (tag.hasKey("itemLev")
                    && tag.getBoolean("itemLev")) {
                list.add("Levitates items");
            }
            if (tag.hasKey("redstone")
                    && tag.getBoolean("redstone")) {
                list.add("Will lower things when redstone is applied");
            }
        }

        if (!list.isEmpty()) {
            for (String s : list) {
                tooltip.add(TextFormatting.GREEN + s);
            }
        } else {
            tooltip.add(TextFormatting.RED + "This won't do anything");
            tooltip.add(TextFormatting.RED + "Add stuff to make it work");
        }
    }

}
