package com.aktheknight.akutils.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * Created by alex on 13/05/16.
 */
public class FireChargeUse {

    @SubscribeEvent
    public void onEvent(PlayerInteractEvent e) {
        if (e.getItemStack() == null) {
            return;
        }
        if (!e.getItemStack().getItem().equals(Items.fire_charge))
            return;
        BlockPos pos = e.getPos();
        BlockPos check;
        int radius = 2;
        Random rand = new Random();
        boolean change;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                change = true;
                check = pos.add(x, 0, z);
                if (x > 1 || x < -1
                        || z > 1 || z < -1) {
                    if (rand.nextInt(3) != 1) {
                        change = false;
                    }
                }
                if (change) {
                    Block block = e.getWorld().getBlockState(check).getBlock();
                    if (block instanceof BlockSand) {
                        e.getWorld().setBlockState(check, Blocks.glass.getDefaultState());
                    }
                    if (block instanceof BlockGrass) {
                        if (rand.nextBoolean()) {
                            e.getWorld().setBlockState(check, Blocks.grass_path.getDefaultState());
                        } else {
                            e.getWorld().setBlockState(check, Blocks.dirt.getDefaultState());
                        }
                    }
                }
            }
        }
    }
}
