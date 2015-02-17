package plugins.mbes.modcommands;

import java.util.HashMap;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.dynamic.ChatColor;

import plugins.mbes.modcommands.Commands;
import plugins.mbes.modcommands.ModCmds;
import plugins.mbes.modcommands.Mute;
import plugins.mbes.modcommands.ReportCmds;
import plugins.mbes.modcommands.MuteHandler;
import plugins.mbes.modcommands.ReportManager;

@Manifest(name="ESModCommands",authors = {"TheMushyPeas","AAAA","Abiram"})

public class ModCommandsPlugin extends MBServerPlugin{

	private HashMap<String,Object>data;
	private ReportManager report;
	public static String tag = ChatColor.GREEN + "[" + ChatColor.MAGENTA + "MBES" + 
	                                      ChatColor.GREEN + "] " + ChatColor.WHITE;
	
	public ModCommandsPlugin() {
		
		data = new HashMap<String,Object>();
		report = new ReportManager();
		
	}
	
	@Override
	public void onEnable() {

		this.getPluginManager().registerCommand("kill",new Commands(this.getServer()));
		this.getPluginManager().registerCommand("sudo",new ModCmds(this.getServer()));
		this.getPluginManager().registerCommand("kickall",new ModCmds(this.getServer()));
		this.getPluginManager().registerCommand("sayas",new ModCmds(this.getServer()));	
		this.getPluginManager().registerCommand("mute",new Mute(this.getServer(),data));
		this.getPluginManager().registerCommand("unmute",new Mute(this.getServer(),data));
		this.getPluginManager().registerEventHandler(new MuteHandler(data));
		
		report = this.getServer().getConfigurationManager().load(this,ReportManager.class);
		if(report == null)
			report = new ReportManager();

		this.getPluginManager().registerCommand("report",new ReportCmds(this.getServer(),report));
		this.getPluginManager().registerCommand("vwreport",new ReportCmds(this.getServer(),report));
		this.getPluginManager().registerCommand("delreport",new ReportCmds(this.getServer(),report));

		report = this.getServer().getConfigurationManager().load(this,ReportManager.class);
		 
		if(report == null)
			report = new ReportManager();
		 
		this.getPluginManager().registerCommand("report",new ReportCmds(this.getServer(),report));
		this.getPluginManager().registerCommand("vwreport",new ReportCmds(this.getServer(),report));
		this.getPluginManager().registerCommand("delreport",new ReportCmds(this.getServer(),report));
		
	}
	
	@Override
	public void onDisable(){
	
		this.getServer().getConfigurationManager().save(this,report);
		
	}
	
}
