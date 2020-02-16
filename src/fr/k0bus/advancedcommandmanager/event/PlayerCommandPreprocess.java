package fr.k0bus.advancedcommandmanager.event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.k0bus.advancedcommandmanager.Main;

public class PlayerCommandPreprocess implements Listener {

	Main plugin;

	public PlayerCommandPreprocess(Main instance) {
		plugin = instance;
	}

	@EventHandler(ignoreCancelled = true)
	public void onGMChange(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage().substring(1).split(" ")[0];
		Player p = e.getPlayer();
		if (plugin.getConfig().getList("log").contains(cmd) && p.hasPermission("acommandmanager.log")) {
			
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dateLogFormat = new SimpleDateFormat("dd_MM_yyyy");
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			String log = "";

			log = dateFormat.format(now) + ";" + hourFormat.format(now) + ";" + e.getPlayer().getName() + ";"
					+ e.getPlayer().getUniqueId() + ";" + cmd + ";" + e.getMessage();
			File logDir = new File(plugin.getDataFolder() + File.separator + "log" + File.separator);
			File logFile = new File(logDir, dateLogFormat.format(now) + ".csv");
			if (!logFile.exists()) {
				logDir.mkdirs();
				try {
					logFile.createNewFile();
					BufferedWriter writer = new BufferedWriter(
						new FileWriter(logFile, true)  //Set true for append mode
					);  
					writer.write("date;time;username;uuid;command;total_command");
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			try {
				BufferedWriter writer = new BufferedWriter(
					new FileWriter(logFile, true)  //Set true for append mode
				);  
				writer.newLine();
				writer.write(log);
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
