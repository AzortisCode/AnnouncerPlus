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

package com.azortis.announcerplus.settings;

import com.azortis.announcerplus.AnnouncerPlus;
import com.azortis.announcerplus.objects.Announcement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@SuppressWarnings("all")
public class SettingsManager {

    private AnnouncerPlus plugin;
    private File settingsFile;
    private File messageFile;

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private Map<String, Object> settingsMap;
    private Map<String, Object> messageMap;
    private Map<String, Announcement> announcementMap;
//    private boolean filesUpToDate = true;

    public SettingsManager(AnnouncerPlus plugin) {
        this.plugin = plugin;
        plugin.getLogger().info("Loading settings...");
        if (plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
        settingsFile = new File(plugin.getDataFolder(), "settings.json");
        messageFile = new File(plugin.getDataFolder(), "messages.json");
        if (!settingsFile.exists()){
            plugin.getLogger().info("Creating new settings file...");
            plugin.saveResource(settingsFile.getName(), false);
        }
        if (!messageFile.exists()){
            plugin.getLogger().info("Creating new message file...");
            plugin.saveResource(messageFile.getName(), false);
        }
        try {
            settingsMap = gson.fromJson(new FileReader(settingsFile), Map.class);
            messageMap = gson.fromJson(new FileReader(messageFile), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Check if settingsFile is up to date!
        /*String settingsFileVersion = (String) settingsMap.get("fileVersion");
        if(!plugin.getUpdateChecker().getPluginVersion().getSettingsFileVersion().equals(settingsFileVersion)){
            plugin.getLogger().severe("Settings file is outdated, Please update it!");
            filesUpToDate = false;
        }
        //Check if messageFile is up to date!
        String messageFileVersion = (String) settingsMap.get("fileVersion");
        if(!plugin.getUpdateChecker().getPluginVersion().getMessageFileVersion().equals(messageFileVersion)){
            plugin.getLogger().severe("Message file is outdated, Please update it!");
            filesUpToDate = false;
        }
        if(!filesUpToDate){
            plugin.getLogger().severe("Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }*/
    }

    /*public boolean areFilesUpToDate() {
        return filesUpToDate;
    }*/

    public void saveSettingsFile() {
        try {
            final String json = gson.toJson(settingsMap);
            settingsFile.delete();
            Files.write(settingsFile.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadSettingsFile() {
        try {
            settingsMap = gson.fromJson(new FileReader(settingsFile), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveMessageFile() {
        try {
            final String json = gson.toJson(messageMap);
            messageFile.delete();
            Files.write(messageFile.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadMessageFile() {
        try {
            messageMap = gson.fromJson(new FileReader(messageFile), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getSettingsMap() {
        return settingsMap;
    }

    public Map<String, Object> getMessageMap() {
        return messageMap;
    }

}
