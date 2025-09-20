package src.me.snowsun.tabprefix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TabPrefix extends JavaPlugin {

    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        for (Player p : Bukkit.getOnlinePlayers()) {
            setupTeam(p);
        }

        getServer().getPluginManager().registerEvents(new TabListener(this), this);
        getLogger().info("TabPrefix запущен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TabPrefix выключен!");
    }

    public void setupTeam(Player player) {
        String group = getGroup(player);
        String prefix = ChatColor.translateAlternateColorCodes('&',
                getConfig().getString("groups." + group, "&7[Игрок] "));

        Team team = scoreboard.getTeam(group);
        if (team == null) {
            team = scoreboard.registerNewTeam(group);
            team.setPrefix(prefix);
        }

        for (Team t : scoreboard.getTeams()) {
            t.removeEntry(player.getName());
        }

        team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }

    private String getGroup(Player player) {
        if (player.hasPermission("tab.admin")) return "Admin";
        if (player.hasPermission("tab.creeper")) return "Creeper";
        if (player.hasPermission("tab.techadmin")) return "TechAdmin";
        if (player.hasPermission("tab.rookie")) return "Rookie";
        if (player.hasPermission("tab.giant")) return "Giant";
        return "default";
    }
}
