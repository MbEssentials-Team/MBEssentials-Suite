package plugins.mbes.modcommands;

import plugins.mbes.modcommands.ModCommandsPlugin;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.game.Player;

public class Commands implements CommandExecutor{
	private Server s;

	public Commands(Server s) {
		this.s = s;
	}

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {

		if(command.equals("kill"))
		{
			if(args.length == 0)
			{
				if(sender.hasPermission("mbes.cmds.kill") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.cmds.*"))
				{

					sender.sendMessage(ModCommandsPlugin.tag + "you have been killed!");
					Player p = (Player)sender;
					p.kill();

				}

				else
				{
					sender.sendMessage(ModCommandsPlugin.tag + "You don't have permission to use this command!");
				}
			}

			else
			{
				if(sender.hasPermission("mbes.cmds.kill") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
				{
					Player temp = s.getPlayer(args[0]);

					if(temp == null)
						sender.sendMessage(ModCommandsPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE +  "' was not found!");
					else
					{
						temp.kill();
						sender.sendMessage(ModCommandsPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE +  "' was killed!" );

					}
				}
			}
		}
	}
}

