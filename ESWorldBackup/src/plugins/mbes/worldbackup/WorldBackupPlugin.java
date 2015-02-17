package plugins.mbes.worldbackup;

import java.io.IOException;

import plugins.mbes.worldbackup.Config;
import plugins.mbes.worldbackup.WorldBackupHandler;
import plugins.mbes.worldbackup.WorldBackup;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;


@Manifest(name="ESWorldBackup",authors = {"TheMushyPeas","AAAA","Abiram"}, config = Config.class)
public class WorldBackupPlugin extends MBServerPlugin{

	private Config config;
	
	public WorldBackupPlugin() {
		
		config = new Config();
		
	}
	
	@Override
	public void onEnable() {
	
		config = this.getConfig();
		this.saveConfig();
		
		this.getPluginManager().registerEventHandler(new WorldBackupHandler(config));
		
		try {
			
			WorldBackup.Backup(this.getServer());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public void onDisable() {
		
		this.saveConfig();
		
	}
	
}
