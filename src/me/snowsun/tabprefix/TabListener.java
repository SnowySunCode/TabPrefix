package src.me.snowsun.tabprefix;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabListener implements Listener {

    private final TabPrefix plugin;

    public TabListener(TabPrefix plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        plugin.setupTeam(e.getPlayer());
    }
}
