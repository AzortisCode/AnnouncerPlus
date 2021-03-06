/*
 * Announce messages like a pro to your players.
 *     Copyright (C) 2020  Azortis
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.azortis.announcerplus;

import com.azortis.announcerplus.settings.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnnouncerPlus extends JavaPlugin {

    private Metrics metrics;
    private SettingsManager settingsManager;

    @Override
    public void onEnable(){
        if (!Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            this.getLogger().severe("PlaceholderAPI isn't present, please install PlaceholderAPI! Shutting down...");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            return;
        }

    }

}
