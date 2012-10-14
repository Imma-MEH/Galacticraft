package micdoodle8.mods.galacticraft.core;

import java.util.List;

import micdoodle8.mods.galacticraft.core.GCContainerAirDistributor;
import micdoodle8.mods.galacticraft.core.GCContainerTankRefill;
import micdoodle8.mods.galacticraft.core.GCCoreInventoryTankRefill;
import micdoodle8.mods.galacticraft.core.GCCoreTileEntityOxygenDistributor;
import micdoodle8.mods.galacticraft.core.client.GCCoreGuiAirDistributor;
import micdoodle8.mods.galacticraft.core.client.GCCoreGuiTankRefill;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * Copyright 2012, micdoodle8
 * 
 *  All rights reserved.
 *
 */
public class CommonProxyCore implements IGuiHandler
{
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public void registerRenderInformation()
	{
		
	}
	
	
	public int getGCUnlitTorchRenderID()
	{
		return -1;
	}
	
	public int getGCBreathableAirRenderID()
	{
		return -1;
	}
	
	public int getGCOxygenPipeRenderID()
	{
		return -1;
	}

	public int getGCTreasureChestRenderID() 
	{
		return -1;
	}
	
	public World getClientWorld()
	{
		return null;
	}
	
	public void displayChoosePlanetGui()
	{
	}
	
    public void spawnParticle(String var1, double var2, double var4, double var6, double var8, double var10, double var12, boolean b) {}
    
    
    // IGUIHANDLER IMPLEMENTATION:
    
    GCCoreInventoryTankRefill inv = null;

    public static List<GCCoreInventoryTankRefill> airTanks = Lists.newArrayList();
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == GCCoreConfigManager.idGuiTankRefill)
		{
			for (int i = 0; i < GalacticraftCore.serverPlayerAPIs.size(); ++i)
	        {
				GCCorePlayerBaseServer playerBase = (GCCorePlayerBaseServer) GalacticraftCore.serverPlayerAPIs.get(i);
				
				if (player.username == playerBase.getPlayer().username)
				{
					return new GCContainerTankRefill(player.inventory, playerBase.playerTankInventory);
				}
	        }
		}
		else if (ID == GCCoreConfigManager.idGuiAirDistributor)
		{
			GCCoreTileEntityOxygenDistributor distributor = (GCCoreTileEntityOxygenDistributor) world.getBlockTileEntity(x, y, z);

			return new GCContainerAirDistributor(player.inventory, distributor);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == GCCoreConfigManager.idGuiTankRefill)
		{
			return new GCCoreGuiTankRefill(player);
		}
		else if (ID == GCCoreConfigManager.idGuiAirDistributor)
		{
			GCCoreTileEntityOxygenDistributor distributor = (GCCoreTileEntityOxygenDistributor) world.getBlockTileEntity(x, y, z);
			
			return new GCCoreGuiAirDistributor(player.inventory, distributor);
		}
		else
		{
			return null;
		}
	}
}