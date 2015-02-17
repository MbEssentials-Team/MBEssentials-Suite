package plugins.mbes.messenger;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.dynamic.ChatColor;

import plugins.mbes.messenger.PMCmds;
import plugins.mbes.messenger.PMBlockHandler;

@Manifest(name="ESMessenger",authors = {"TheMushyPeas","AAAA","Abiram"})

public class MessengerPlugin extends MBServerPlugin {

	public static String tag = ChatColor.GREEN + "[" + ChatColor.MAGENTA + "MBES" + 
	                                       ChatColor.GREEN + "] " + ChatColor.WHITE;
	
	@Override
	public void onEnable(){
	
		this.getPluginManager().registerCommand("pm",new PMCmds(this.getServer()));		
		this.getPluginManager().registerCommand("unblock",new PMCmds(this.getServer()));
		this.getPluginManager().registerCommand("block",new PMCmds(this.getServer()));
		this.getPluginManager().registerCommand("blockall",new PMCmds(this.getServer()));
		this.getPluginManager().registerCommand("unblockall",new PMCmds(this.getServer()));
		this.getPluginManager().registerEventHandler(new PMBlockHandler());	
	
	}
	
}
