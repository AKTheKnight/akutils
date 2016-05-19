package com.aktheknight.akutils.events;

import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 13/05/16.
 */
public class FireChargeUse {

    /*
    And hmm what about crops->grass->pod soil
    ->mycelium->coarse dirt->gravel->cobble
    ->stone->stone brick->cracked stone brick
    ->sand->glass->obsidian->netherrack->lava
    ->coal->red stone->glow stone->quartz
    ->sponge->prizmarine->purplur?
    Just an idea of a cool progression of transmutation idea
     */

    @SubscribeEvent
    public void onEvent(PlayerInteractEvent.RightClickBlock e) {
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
                    if (rand.nextInt(4) != 1) {
                        change = false;
                    }
                } else {
                    if (rand.nextInt(2) != 1) {
                        change = false;
                    }
                }
                done:
                if (change
                        && e.getWorld().getBlockState(check.up()).getBlock().equals(Blocks.air)) {
                    Block block = e.getWorld().getBlockState(check).getBlock();

                    List<ItemStack> drops = block.getDrops(e.getWorld(), check, e.getWorld().getBlockState(check), 0);

                    try {
                        if (!drops.isEmpty()
                                && drops.size() > 0) {
                            if (drops.get(0).getItem() instanceof ItemBlock) {
                                Block place = Block.getBlockFromItem(FurnaceRecipes.instance().getSmeltingResult(drops.get(0)).getItem());
                                e.getWorld().setBlockState(check, place.getDefaultState());
                            }
                        }
                    } catch (Exception ex) {
                        //TODO
                        //Ignore for now
                    }


/*
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
                    if (block == Blocks.cobblestone) {
                        e.getWorld().setBlockState(check, Blocks.stone.getDefaultState());
                    }
                    */
                }
                if (rand.nextInt(10) == 1
                        && e.getWorld().getBlockState(check.up()).getBlock().equals(Blocks.air)
                        && !e.getWorld().getBlockState(check).getBlock().equals(Blocks.air)) {
                    e.getWorld().setBlockState(check.up(), Blocks.fire.getDefaultState());
                }
            }
        }
    }
}
