package plugins.mbes.modcommands;

import plugins.mbes.modcommands.ModCommandsPlugin;
import plugins.mbes.modcommands.ReportManager;
import plugins.mbes.modcommands.Report;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.dynamic.ChatColor;
import com.mbserver.api.game.Player;

public class ReportCmds implements CommandExecutor{

	private Server server;
	private ReportManager report;
	
	public ReportCmds(Server s,ReportManager rp) {
		server = s;
		report = rp;
	}
	
	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		
		if(command.equals("report"))
		{
			if(sender instanceof Player)
			{
				if(sender.hasPermission("mbes.cmds.report") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.cmds.*"))
				{
					if(args.length > 1)
					{	
						Player rp = server.getPlayer(args[0]);
						
						if(rp != null)
						{
							String reason = "";
						
							for(int a = 1; a < args.length;a++)
							{
								reason = reason + args[a] + " ";
							}
							
							report.newReport(new Report((Player)sender,rp,reason));
							sender.sendMessage(ModCommandsPlugin.tag + "Your report has been sent to be reviewed by the moderators!");
						}
						
						else
							sender.sendMessage(ModCommandsPlugin.tag + "The player '" + ChatColor.RED + args[0] + ChatColor.WHITE + "' was not found!");
					}
					else
						sender.sendMessage(ModCommandsPlugin.tag + "Syntax: " + ChatColor.RED + "/report " + ChatColor.GREEN + "<player> <reason>");
				}
				
				else
					sender.sendMessage(ModCommandsPlugin.tag + "You don't have permission to use this command!");
			}
			
			else
				sender.sendMessage(ModCommandsPlugin.tag + "This command can only be executed as a player!");
		}
		
		else if(command.equals("vwreport") && args.length == 0)
		{
			if(sender.hasPermission("mbes.mod.vwreports") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				Report[] rps = report.getAll();
				
				if(rps == null)
					sender.sendMessage(ModCommandsPlugin.tag + "There are no reports!");
				
				else
				{
					sender.sendMessage(ModCommandsPlugin.tag + "  Reports\n-------------");
					for(Report e : rps)
					{
						sender.sendMessage( e.getId() + " " + e.getName());
					}
				}
			}
			else
				sender.sendMessage(ModCommandsPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("vwreport"))
		{
			
			if(sender.hasPermission("mbes.mod.vwreport") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				try{
					int vnum = Integer.parseInt(args[0]);
					
					Report rp = report.getReport(new Report(vnum));
					
					if(rp == null)
						sender.sendMessage(ModCommandsPlugin.tag + "The report '" + ChatColor.RED + vnum + ChatColor.WHITE + "' was not found!");
					
					else
					{
						sender.sendMessage(ModCommandsPlugin.tag + "'" + ChatColor.RED + rp.getName() + ChatColor.WHITE + "' reported " + ChatColor.RED + rp.getReported() + ChatColor.WHITE + "' for:");
						sender.sendMessage(ModCommandsPlugin.tag + ChatColor.RED + rp.getReason());
					}
				}catch(NumberFormatException e){
					sender.sendMessage(ModCommandsPlugin.tag + "Enter a valid number");
				}
			}
			else
				sender.sendMessage(ModCommandsPlugin.tag + "You don't have permission to use this command!");
		}
		
		else if(command.equals("delreport"))
		{
			if(sender.hasPermission("mbes.mod.delreport") || sender.hasPermission("mbes.*") || sender.hasPermission("mbes.mod.*"))
			{
				if(args.length == 0)
					sender.sendMessage(ModCommandsPlugin.tag + "Syntax: " + ChatColor.RED + "/delreport " + ChatColor.GREEN + "<number>");
				
				else
				{
					try{
						int vnum = Integer.parseInt(args[0]);
						
						if(report.delReport(new Report(vnum))){
							sender.sendMessage(ModCommandsPlugin.tag + "The report was deleted!");
							report.update();
						}
						else
							sender.sendMessage(ModCommandsPlugin.tag + "The report '" + ChatColor.RED + vnum + ChatColor.WHITE + "' was not found!");
					}catch(NumberFormatException e){
						sender.sendMessage(ModCommandsPlugin.tag + "Enter a valid number!");
					}
				}
			}
			else
				sender.sendMessage(ModCommandsPlugin.tag + "You don't have permission to use this command!");
		}
		
	}

}
