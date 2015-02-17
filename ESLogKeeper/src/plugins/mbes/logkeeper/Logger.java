package plugins.mbes.logkeeper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import plugins.mbes.logkeeper.LogManager;



public class Logger {
	private BufferedWriter out;
	private int ID;
	
	
	
	/**
	 * @param folder The folder in which the log will be stored
	 *Example Logger lg = new Logger("logs\\MyLogFolder\\");
	 */
	public Logger(String folder) {
		try {
			out = new BufferedWriter(new FileWriter(folder + LogManager.getExactDate() + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param file - The file you want to write to this file should not exist
	 */
	public Logger(File file){
		try{
			out = new BufferedWriter(new FileWriter(file));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * You should not use this method
	 */
	public BufferedWriter getWriter(){
		return out;
	}
	
	/**
	 * You should not use this method
	 * When you attach this logger to a LogManager it will set the ID
	 */
	public void setId(int id){
		ID = id;
	}
	
	
	/**
	 * @return The ID of the logger. Used when logging
	 */
	public int getId() {
		return ID;
	}
	
	/**
	 * You should not use this method
	 */
	public void close() throws IOException{
		out.close();
	}
	
	 @Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Logger) || obj == null) return false;
		
		if(((Logger)obj).getId() == this.getId())
			return true;
		
		return false;
	}
}
