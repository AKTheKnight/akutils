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

public class SuperGrowthAccelerator extends Block {
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
        super(Material.rock);

        this.fixedRandom = new FixedRandom();

        this.setTickRandomly(true);
        this.setHardness(2F);
        this.setStepSound(SoundType.STONE);
        this.setUnlocalizedName("supergrowthaccelerator");
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
        //System.out.println("Random Tick " + pos.toString());

        BlockPos pos_above = pos.up();
        Block block_above = world.getBlockState(pos_above).getBlock();

        if (block_above instanceof GrowthAccelerator) {
            //System.out.println("In a stack");
            block_above.randomTick(world, pos_above, world.getBlockState(pos_above), new Random());
            return;
        }

        BlockPos centre = pos_above.up();
        int radius = ConfigHandler.SuperGrowthAccRadius;
        BlockPos plant_pos;
        IBlockState plant_state;
        Block plant_block;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                plant_pos = centre.add(x, 0, z);
                plant_state = world.getBlockState(plant_pos);
                plant_block = plant_state.getBlock();


                //Melon and Pumpkin stems
                if (plant_block instanceof BlockStem) {
                    if (BlockStem.getStateId(world.getBlockState(pos_above.up())) >= 7) {
                        plant_block.updateTick(world, plant_pos, state, rand);
                    }
                    else {
                        plant_block.updateTick(world, plant_pos, state, this.fixedRandom);
                    }
                }

                /*
                //Cactus and Sugar Canes
                else if (plant_block instanceof BlockReed || plant_block instanceof BlockCactus) {
                    for (int l = 1; !world.isAirBlock(pos_above.up()) && l < 3; l++) {
                        world.getBlockState(pos_above.up(l)).getBlock().updateTick(world, pos_above.up(l), world.getBlockState(pos_above.up(l)), this.fixedRandom);
                    }
                }
                */

                else if(plant_block instanceof BlockReed) {
                    BlockReed reed = (BlockReed) plant_block;
                    int i;
                    for(i = 1; world.getBlockState(plant_pos.up(i)).getBlock() == reed; ++i);
                    if (i < 4) {
                        world.setBlockState(plant_pos.up(i), Blocks.reeds.getDefaultState());
                    }
                }

                else if(plant_block instanceof BlockCactus) {
                    BlockCactus cactus = (BlockCactus) plant_block;
                    int i;
                    for(i = 1; world.getBlockState(plant_pos.up(i)).getBlock() == cactus; ++i);
                    if (i < 4) {
                        world.setBlockState(plant_pos.up(i), Blocks.cactus.getDefaultState());
                    }
                }

                else if (plant_block instanceof IPlantable) {
                    IGrowable plant = (IGrowable) plant_block;
                    if (plant.canGrow(world, plant_pos, world.getBlockState(plant_pos), false)) {
                        plant.grow(world, new Random(123), plant_pos, world.getBlockState(plant_pos));
                    }
                    else {
                        plant_block.updateTick(world, plant_pos, world.getBlockState(plant_pos), this.fixedRandom);
                    }
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.AQUA + "Makes things grow \"super\" fast ;)");
        tooltip.add(TextFormatting.GOLD + "Affects an area of crops");
    }
}
