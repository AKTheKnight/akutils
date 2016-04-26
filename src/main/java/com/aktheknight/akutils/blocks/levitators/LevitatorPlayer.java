package com.aktheknight.akutils.blocks.levitators;

import com.aktheknight.akutils.blocks.tileentities.TELevitatorPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by alex on 26/04/16.
 */
public class LevitatorPlayer extends LevitatorBase {

    public LevitatorPlayer() {
        super();
        this.setUnlocalizedName("levitatorplayer");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TELevitatorPlayer();
    }
}
