package plugins.mbes.logkeeper;

import java.io.File;
import java.io.IOException;

import plugins.mbes.logkeeper.LogHandler;
import plugins.mbes.logkeeper.LogManager;
import plugins.mbes.logkeeper.Logger;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;

@Manifest(name="ESLogKeeper",authors = {"TheMushyPeas","AAAA","Abiram"})
public class LogKeeperPlugin extends MBServerPlugin{

	private LogManager logm;
	private Logger breakLog,placeLog,deathLog,PvPLog,cmdLog;
	
	@Override
	public void onLoad() {
	
		String[] fileNames = {"logs/ESLogKeeper","logs/ESLogKeeper/Command_Logs","logs/ESLogKeeper/Death_Logs"
				,"logs/ESLogKeeper/PvP_Logs","logs/ESLogKeeper/Place_Logs","logs/ESLogKeeper/Break_Logs",};
		
		File file;
		
		for(String e : fileNames)
		{
			file = new File(e);
			
			if(!file.exists())
				file.mkdir();
		}
		
	}
	
	@Override
	public void onEnable(){
		
		logm = new LogManager();
		 
		breakLog = new Logger("logs/ESLogKeeper/Break_Logs/");
		logm.attachLogger(breakLog);

		cmdLog = new Logger("logs/ESLogKeeper/Command_Logs/");
		logm.attachLogger(cmdLog);



		deathLog = new Logger("logs/ESLogKeeper/Death_Logs/");
		logm.attachLogger(deathLog);



		placeLog = new Logger("logs/ESLogKeeper/Place_Logs/");
		logm.attachLogger(placeLog);



		PvPLog = new Logger("logs/ESLogKeeper/PvP_Logs/");
		logm.attachLogger(PvPLog);

		 
		 int[] lgs = new int[5];
		 
		 if(deathLog != null)
			 lgs[0] = deathLog.getId();
		 
		 if(PvPLog != null)
			 lgs[1] = PvPLog.getId();
		 
		 if(cmdLog != null)
			 lgs[2] = cmdLog.getId();
		 
		 if(placeLog != null)
			 lgs[3] = placeLog.getId();
		 
		 if(breakLog != null)
			 lgs[4] = breakLog.getId();
		 
		 this.getPluginManager().registerEventHandler(new LogHandler(logm,lgs));
		
	}
	
	@Override
	public void onDisable(){
		
		if(logm != null) {
				
			try {

				logm.closeAll();

			} catch (IOException e) {

				e.printStackTrace();

				this.getLogger().warning("Please report this error to the Space Walrus forums, and we will try and help you!");

			}
			
		}
		
	}
	
	public LogManager getLogManager(){
		return logm;
	}
	
}
