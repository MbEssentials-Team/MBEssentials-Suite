package plugins.mbes.moneysystem;

import plugins.mbes.moneysystem.MoneyCmds;
import plugins.mbes.moneysystem.AccountMaker;
import plugins.mbes.moneysystem.MoneyManager;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.dynamic.ChatColor;

@Manifest(name="ESLogKeeper",authors = {"TheMushyPeas","AAAA","Abiram"})

public class MoneySystemPlugin extends MBServerPlugin{
	
	public static String tag = ChatColor.GREEN + "[" + ChatColor.MAGENTA + "MBES" + 
	                                      ChatColor.GREEN + "] " + ChatColor.WHITE;
	private MoneyManager bank;
	
	@Override
	public void onEnable() {
		
		bank = this.getServer().getConfigurationManager().load(this,MoneyManager.class);
		 
		if(bank == null)
			bank = new MoneyManager();
		 
		this.getPluginManager().registerCommand("pay",new MoneyCmds(this.getServer(),bank));
		this.getPluginManager().registerCommand("addmoney",new MoneyCmds(this.getServer(),bank));
		this.getPluginManager().registerCommand("rmvmoney",new MoneyCmds(this.getServer(),bank));
	    this.getPluginManager().registerCommand("balance",new MoneyCmds(this.getServer(),bank));
	    this.getPluginManager().registerCommand("resetmoney",new MoneyCmds(this.getServer(),bank));
		this.getPluginManager().registerEventHandler(new AccountMaker(bank));
	
	}
	
	@Override
	public void onDisable() {
	
		if(bank != null) {
			
			this.getServer().getConfigurationManager().save(this,bank);
			
		}
		
	}

}
