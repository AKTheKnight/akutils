package com.aktheknight.akutils.items;

import com.aktheknight.akutils.AKUtils;
import com.aktheknight.akutils.ConfigHandler;
import com.aktheknight.akutils.util.FixedRandom;
import net.minecraft.block.*;
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
import java.util.Random;

public class SuperMeal extends Item {
    /**
     * blb
     * bgb
     * blb
     * b: bonemeal
     * l: lapis
     * g: gold
     */

    private FixedRandom fixedRandom;

    public SuperMeal() {
        this.maxStackSize = 64;
        this.setCreativeTab(AKUtils.akUtilsTab);
        this.setUnlocalizedName("supermeal");
        this.fixedRandom = new FixedRandom();
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            BlockPos growPos;
            Block plantBlock;
            IGrowable plant;
            int radius = ConfigHandler.SuperMealRadius;
            int numGrowable = 0;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    growPos = pos.add(x, 0, z);
                    plantBlock = world.getBlockState(growPos).getBlock();
                    if (plantBlock instanceof IGrowable) {
                        numGrowable++;
                        plant = (IGrowable) plantBlock;
                        if (plantBlock instanceof BlockSapling) {
                            for(int i = 0; i < 2; i ++) {
                                if (world.getBlockState(growPos).getBlock() == plantBlock) {
                                    plant.grow(world, this.fixedRandom, growPos, world.getBlockState(growPos));
                                    try {
                                        Thread.sleep(20);
                                    }
                                    catch (Exception e) {
                                        return EnumActionResult.FAIL;
                                    }
                                }
                            }
                        }
                        if (plant.canGrow(world, growPos, world.getBlockState(growPos), false)) {
                            plant.grow(world, new Random(123), growPos, world.getBlockState(growPos));
                        }
                        else {
                            plantBlock.updateTick(world, growPos, world.getBlockState(growPos), this.fixedRandom);
                        }
                    }
                    if(plantBlock instanceof BlockReed) {
                        numGrowable++;
                        BlockReed reed = (BlockReed) plantBlock;
                        int down;
                        for(down = 1; world.getBlockState(growPos.down(down)).getBlock() == reed; down++);
                        if (down < 4) {
                            int up;
                            for(up = 0; world.getBlockState(growPos.up(up + 1)).getBlock() == reed; up++);
                            if ((up + down) < 4) {
                                world.setBlockState(growPos.up(up + 1), Blocks.REEDS.getDefaultState());
                            }
                        }
                    }
                    if(plantBlock instanceof BlockCactus) {
                        numGrowable++;
                        BlockCactus reed = (BlockCactus) plantBlock;
                        int down;
                        for(down = 1; world.getBlockState(growPos.down(down)).getBlock() == reed; down++);
                        if (down < 4) {
                            int up;
                            for(up = 0; world.getBlockState(growPos.up(up + 1)).getBlock() == reed; up++);
                            if ((up + down) < 4) {
                                world.setBlockState(growPos.up(up + 1), Blocks.CACTUS.getDefaultState());
                            }
                        }
                    }
                }
            }
            if (numGrowable > 0) {
                --stack.stackSize;
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.AQUA + "No, not a food");
        tooltip.add(TextFormatting.GOLD + "Bonemeals a 3x3 area (yes it's magic)");
    }
}
