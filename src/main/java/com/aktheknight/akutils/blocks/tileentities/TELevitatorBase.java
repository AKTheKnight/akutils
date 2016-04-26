package com.aktheknight.akutils.blocks.tileentities;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by alex_ on 31/03/2016.
 */
public class TELevitatorBase extends TileEntity implements ITickable{

    void doLevitate(Entity entity, int blockY) {
        int entityY = entity.getPosition().getY();
        if (!entity.isSneaking()) {
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

    @Override
    public void update() {
        AxisAlignedBB area = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 7, pos.getZ() + 1);
        List<Entity> entities = worldObj.getEntitiesWithinAABB(Entity.class, area);
        int blockY = this.pos.getY();
        for (Entity entity: entities) {
            doLevitate(entity, blockY);
        }
    }
}
