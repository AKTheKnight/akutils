package com.aktheknight.akutils.items;

import com.aktheknight.akutils.AKUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
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

public class TestingStick extends Item {
	
	public TestingStick() {
		this.maxStackSize = 1;
		this.setCreativeTab(AKUtils.akUtilsTab);
		this.setUnlocalizedName("testingstick");
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		//System.out.println("Testing");
		if (!world.isRemote) {
			System.out.println(pos + "   :   " + world.getBlockState(pos).getBlock() + "   :   " + world.getBlockState(pos));
			if (player.isSneaking()) {
				IBlockState b = world.getBlockState(pos);
				Block bc = b.getBlock();
				bc.randomTick(world, pos, b, new Random());
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(TextFormatting.AQUA + "No, not for playing with");
		tooltip.add(TextFormatting.GOLD + "Magic");
	}
	
//	THIS IS BROKEN. USE IF YOU WANT
/*	private boolean hasBlock(NBTTagCompound comp) {
		if (comp != null && comp.hasKey("hasBlock")) {
			return true;
		}
		return false;
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote == false) {
            NBTTagCompound comp = stack.getTagCompound();
            if (hasBlock(comp)) {
                placeBlock(stack, world, x, y, z, side);
            }
            else {
                pickupBlock(stack, player, world, x, y, z, side);
            }
            return true;
        }
        return false;
    }
	
	private void placeBlock(ItemStack stack, World world, int x, int y, int z, int side) {
        int xPlace = x + ForgeDirection.getOrientation(side).offsetX;
        int yPlace = y + ForgeDirection.getOrientation(side).offsetY;
        int zPlace = z + ForgeDirection.getOrientation(side).offsetZ;
        NBTTagCompound tagComp = stack.getTagCompound();
        int blockID = tagComp.getInteger("block");
        Block block = (Block) Block.blockRegistry.getObjectById(blockID);
        int meta = tagComp.getInteger("meta");

        world.setBlock(xPlace, yPlace, zPlace, block, meta, 3);
        world.setBlockMetadataWithNotify(xPlace, yPlace, zPlace, meta, 3);
        if (tagComp.hasKey("tedata")) {
            NBTTagCompound tagCompBlock = (NBTTagCompound) tagComp.getTag("tedata");
            TileEntity tileEntity = world.getTileEntity(xPlace, yPlace, zPlace);
            if (tileEntity != null) {
            	tagCompBlock.setInteger("x", xPlace);
            	tagCompBlock.setInteger("y", yPlace);
            	tagCompBlock.setInteger("z", zPlace);
                tileEntity.readFromNBT(tagCompBlock);
                tileEntity.markDirty();
                world.markBlockForUpdate(xPlace, yPlace, zPlace);
            }
        }

        tagComp.removeTag("block");
        tagComp.removeTag("tiledata");
        tagComp.removeTag("meta");
        stack.setTagCompound(tagComp);
    }

    private void pickupBlock(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side) {
    	NBTTagCompound tagCompound = Util.getTags(stack);
    	if (side == 1) {
    		
    		Block block1 = world.getBlock(x-1, y, z-1);
    		Block block2 = world.getBlock(x, y, z-1);
    		Block block3 = world.getBlock(x+1, y, z-1);
    		
    		Block block4 = world.getBlock(x-1, y, z);
    		Block block5 = world.getBlock(x, y, z);
    		Block block6 = world.getBlock(x+1, y, z);
    		
    		Block block7 = world.getBlock(x-1, y, z+1);
    		Block block8 = world.getBlock(x, y, z+1);
    		Block block9 = world.getBlock(x+1, y, z+1);
    		
    		int meta1 = world.getBlockMetadata(x-1, y, z-1);
    		int meta2 = world.getBlockMetadata(x, y, z-1);
    		int meta3 = world.getBlockMetadata(x+1, y, z-1);
    		
    		int meta4 = world.getBlockMetadata(x-1, y, z);
    		int meta5 = world.getBlockMetadata(x, y, z);
    		int meta6 = world.getBlockMetadata(x+1, y, z);
    		
    		int meta7 = world.getBlockMetadata(x-1, y, z+1);
    		int meta8 = world.getBlockMetadata(x, y, z+1);
    		int meta9 = world.getBlockMetadata(x+1, y, z+1);
    		
    		int id1 = Block.blockRegistry.getIDForObject(block1);
    		int id2 = Block.blockRegistry.getIDForObject(block2);
    		int id3 = Block.blockRegistry.getIDForObject(block3);
    		
    		int id4 = Block.blockRegistry.getIDForObject(block4);
    		int id5 = Block.blockRegistry.getIDForObject(block5);
    		int id6 = Block.blockRegistry.getIDForObject(block6);
    		
    		int id7 = Block.blockRegistry.getIDForObject(block7);
    		int id8 = Block.blockRegistry.getIDForObject(block8);
    		int id9 = Block.blockRegistry.getIDForObject(block9);
    		
    		tagCompound.setInteger("block1", id1);
            tagCompound.setInteger("meta1", meta1);
            tagCompound.setInteger("block2", id2);
            tagCompound.setInteger("meta2", meta2);
            tagCompound.setInteger("block3", id3);
            tagCompound.setInteger("meta3", meta3);
            
            tagCompound.setInteger("block4", id4);
            tagCompound.setInteger("meta4", meta4);
            tagCompound.setInteger("block5", id5);
            tagCompound.setInteger("meta5", meta5);
            tagCompound.setInteger("block6", id6);
            tagCompound.setInteger("meta6", meta6);
            
            tagCompound.setInteger("block4", id7);
            tagCompound.setInteger("meta4", meta7);
            tagCompound.setInteger("block5", id8);
            tagCompound.setInteger("meta5", meta8);
            tagCompound.setInteger("block6", id9);
            tagCompound.setInteger("meta6", meta9);
    		
            TileEntity tileEntity1 = world.getTileEntity(x-1, y, z-1);
            TileEntity tileEntity2 = world.getTileEntity(x, y, z-1);
            TileEntity tileEntity3 = world.getTileEntity(x+1, y, z-1);
    		
            TileEntity tileEntity4 = world.getTileEntity(x-1, y, z);
            TileEntity tileEntity5 = world.getTileEntity(x, y, z);
            TileEntity tileEntity6 = world.getTileEntity(x+1, y, z);
    		
            TileEntity tileEntity7 = world.getTileEntity(x-1, y, z+1);
            TileEntity tileEntity8 = world.getTileEntity(x, y, z+1);
            TileEntity tileEntity9 = world.getTileEntity(x+1, y, z+1);
            
            if (tileEntity1 == null 
            		&& tileEntity2 == null 
            		&& tileEntity3 == null 
            		&& tileEntity4 == null 
            		&& tileEntity5 == null 
            		&& tileEntity6 == null 
            		&& tileEntity7 == null 
            		&& tileEntity8 == null 
            		&& tileEntity9 == null) {
            	world.setBlockToAir(x-1, y, z-1);
            	world.setBlockToAir(x, y, z-1);
            	world.setBlockToAir(x+1, y, z-1);
                 
            	world.setBlockToAir(x-1, y, z);
            	world.setBlockToAir(x, y, z);
            	world.setBlockToAir(x+1, y, z);
         		
            	world.setBlockToAir(x-1, y, z+1);
            	world.setBlockToAir(x, y, z+1);
            	world.setBlockToAir(x+1, y, z+1);
            }
            else {
            	Util.error(player, "You can't select one or more of these blocks");
            }
            
            tagCompound.setBoolean("hasBlocks", true);
    	}
    	else {
        	Util.error(player, "You can't select one or more of these blocks");
        }
        
    }
	*/
}
