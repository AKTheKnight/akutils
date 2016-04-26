package com.aktheknight.akutils.blocks.levitators;

import com.aktheknight.akutils.blocks.tileentities.TELevitatorItems;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by alex on 26/04/16.
 */
public class LevitatorItems extends LevitatorBase {

    public LevitatorItems() {
        super();
        this.setUnlocalizedName("levitatoritems");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TELevitatorItems();
    }
}
