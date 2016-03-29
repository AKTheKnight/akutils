package com.aktheknight.akutils.blocks;

import com.aktheknight.akutils.util.FixedRandom;
import com.aktheknight.akutils.util.StemFixedRandom;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.List;
import java.util.Random;

public class GrowthAccelerator extends Block {
    /**
     * srs
     * rpr
     * brb
     * s: seed
     * r: redstone
     * p: piston
     * b: stone
     */

    private StemFixedRandom stemRandom;
    private FixedRandom fixedRandom;

    public GrowthAccelerator() {
        super(Material.rock);

        this.stemRandom = new StemFixedRandom();
        this.fixedRandom = new FixedRandom();

        this.setTickRandomly(true);
        this.setHardness(2F);
        this.setStepSound(SoundType.STONE);
        this.setUnlocalizedName("growthaccelerator");
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void randomTick(World world, BlockPos pos, IBlockState state, Random prng) {
        //System.out.println("Random Tick " + pos.toString());

        BlockPos pos_above = pos.up();
        Block block_above = world.getBlockState(pos_above).getBlock();

        if (world.isAirBlock(pos_above)) {
            return;
        }

        if (block_above instanceof GrowthAccelerator) {
            //System.out.println("In a stack");
            block_above.randomTick(world, pos_above, world.getBlockState(pos_above), new Random());
            return;
        }

        if (world.isAirBlock(pos_above.up())) {
            return;
        }

        BlockPos plant_pos = pos_above.up();
        Block plant_block = world.getBlockState(plant_pos).getBlock();

        //Melon and Pumpkin stems
        if (plant_block instanceof BlockStem) {
            BlockStem stem = (BlockStem)plant_block;
            if (BlockStem.getStateId(world.getBlockState(plant_pos)) >= 7) {
                if (stem.canGrow(world, plant_pos, world.getBlockState(plant_pos), false)) {
                    stem.grow(world, this.stemRandom, plant_pos, world.getBlockState(plant_pos));
                }
                else {
                    plant_block.updateTick(world, plant_pos, world.getBlockState(plant_pos), this.stemRandom);
                }
            }
            else {
                plant_block.updateTick(world, plant_pos, world.getBlockState(plant_pos), this.fixedRandom);
            }
        }

        //Cactus and Sugar Canes
        else if (plant_block instanceof BlockReed || plant_block instanceof BlockCactus) {
            for (int l = 1; !world.isAirBlock(pos_above.up()) && l < 3; l++) {
                world.getBlockState(pos_above.up(l)).getBlock().updateTick(world, pos_above.up(l), state, this.fixedRandom);
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
