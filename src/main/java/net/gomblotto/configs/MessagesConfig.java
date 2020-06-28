package net.gomblotto.configs;

import net.gomblotto.StatsCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfig implements ConfigService {
    private File file;
    private FileConfiguration yamlConfiguration;

    @Override
    public void init() {
        this.file = new File(StatsCore.getInstance().getDataFolder(), "messages.yml");
        this.create(true);
    }

    @Override
    public void saveConfig() {
        try {
            this.yamlConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    @Override
    public void reload() {
        try {
            this.yamlConfiguration.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();
        }
    }

    public void create(boolean saveResource) {
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            if (!saveResource) {
                try {
                    this.file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else StatsCore.getInstance().saveResource("messages.yml", StatsCore.getInstance().getResource("messages.yml") == null);
        }
        this.load();
    }

    public FileConfiguration getYamlConfiguration() {
        return this.yamlConfiguration;
    }
}
