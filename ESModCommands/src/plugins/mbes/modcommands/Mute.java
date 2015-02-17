package plugins.mbes.modcommands;

import java.util.HashMap;

import plugins.mbes.modcommands.ModCommandsPlugin;
import plugins.mbes.modcommands.Keys;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.game.Player;

public class Mute implements CommandExecutor{

	private Server server;
	private HashMap<String,Object>data;
	
	public Mute(Server server,HashMap<String,Object> map) {
		this.server = server;
		data = map;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("mute"))
		{
			if(sender.hasPermission("mbes.mod.mute") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length == 0)
					sender.sendMessage(ModCommandsPlugin.tag + "Syntax: " + ChatColor.RED + "/mute  " + ChatColor.GREEN + "<name>");
				else
				{
					Player p = server.getPlayer(args[0]);
				
					if(p == null)
						sender.sendMessage(ModCommandsPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
				
					else
					{
						data.put(p.getLoginName() + Keys.mute_key,true);
						sender.sendMessage(ModCommandsPlugin.tag + "The player '" + ChatColor.RED + p.getDisplayName() + ChatColor.WHITE + "' has been muted!");
					}
				}
			}
		
			else
				sender.sendMessage(ModCommandsPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("unmute"))
		{
			if(sender.hasPermission("mbes.mod.unmute") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length <= 0)
					sender.sendMessage(ModCommandsPlugin.tag + "Syntax: " + ChatColor.RED + "/unmute  " + ChatColor.GREEN + "<name>");
				else
				{
					Player p = server.getPlayer(args[0]);
				
					if(p == null)
						sender.sendMessage(ModCommandsPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
				
					else
					{
						data.remove(p.getLoginName() + Keys.mute_key);
						sender.sendMessage(ModCommandsPlugin.tag + "The player '" + ChatColor.RED + p.getDisplayName() + ChatColor.WHITE + "' has been unmuted!");
					}
				}
			}
		
			else
				sender.sendMessage(ModCommandsPlugin.tag + "You don't have permission to use this command!");
		}
	}

}
