package com.aktheknight.akutils.blocks.levitators;

import com.aktheknight.akutils.blocks.tileentities.TELevitatorItems;
import com.aktheknight.akutils.blocks.tileentities.TELevitatorMobs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by alex on 26/04/16.
 */
public class LevitatorMobs extends LevitatorBase {

    public LevitatorMobs() {
        super();
        this.setUnlocalizedName("levitatormobs");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TELevitatorMobs();
    }
}
