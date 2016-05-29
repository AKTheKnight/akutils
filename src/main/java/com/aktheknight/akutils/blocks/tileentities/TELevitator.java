package com.aktheknight.akutils.blocks.tileentities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by alex_ on 31/03/2016.
 */
public class TELevitator extends TileEntity implements ITickable {

    public boolean mobLev = false;
    public boolean playerLev = false;
    public boolean itemLev = false;
    public boolean redstone = false;

    void doLevitate(Entity entity, int blockY) {
        int entityY = entity.getPosition().getY();
        if (!entity.isSneaking() && hasRedstone()) {
            if (entityY > blockY) {
                int dif = entityY - blockY;
                if (dif > 6) {
                    entity.setVelocity(entity.motionX, 0.4f / (dif - 5), entity.motionZ);
                }
                else {
                    entity.setVelocity(entity.motionX, 0.4f, entity.motionZ);
                }

            }
            if (entityY == blockY) {
                entity.setVelocity(entity.motionX, 0.4f, entity.motionZ);
            }
        }
        else {
            if (entityY > blockY) {
                int dif = entityY - blockY;
                if (dif > 2) {
                    entity.setVelocity(entity.motionX, -0.4f, entity.motionZ);
                }
                else {
                    entity.setVelocity(entity.motionX, -0.2f, entity.motionZ);
                }
            }
            else {
                entity.setVelocity(entity.motionX, -0.1f, entity.motionZ);
            }

        }
        entity.fallDistance = 0f;
    }

    public boolean hasRedstone() {
        if (!redstone)
            return true;
        if (worldObj.isBlockPowered(pos))
            return false;
        return true;
    }

    @Override
    public void update() {
        AxisAlignedBB area = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 7, pos.getZ() + 1);
        int blockY = this.pos.getY();
        if (itemLev) {
            List<EntityItem> entities = worldObj.getEntitiesWithinAABB(EntityItem.class, area);
            for (EntityItem entity: entities) {
                doLevitate(entity, blockY);
            }
        }
        if (mobLev) {
            List<EntityLivingBase> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, area);
            for (EntityLivingBase entity: entities) {
                doLevitate(entity, blockY);
            }
        }
        if (playerLev) {
            List<EntityPlayer> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, area);
            for (EntityPlayer entity: entities) {
                doLevitate(entity, blockY);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        mobLev = nbt.getBoolean("mobLev");
        playerLev = nbt.getBoolean("playerLev");
        itemLev = nbt.getBoolean("itemLev");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("mobLev", mobLev);
        nbt.setBoolean("playerLev", playerLev);
        nbt.setBoolean("itemLev", itemLev);
        return nbt;
    }
}
