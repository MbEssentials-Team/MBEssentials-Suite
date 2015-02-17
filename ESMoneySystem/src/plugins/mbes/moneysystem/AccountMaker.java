package plugins.mbes.moneysystem;

import plugins.mbes.moneysystem.MoneyAccount;
import plugins.mbes.moneysystem.MoneyManager;

import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.PlayerLoginEvent;

public class AccountMaker implements Listener{
	
	private MoneyManager bank;
	
	public AccountMaker(MoneyManager m) {
		this.bank = m;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		
		@SuppressWarnings("unused") // shut up eclipse
		boolean made = bank.addAccount(new MoneyAccount(e.getPlayer()));

	}
}