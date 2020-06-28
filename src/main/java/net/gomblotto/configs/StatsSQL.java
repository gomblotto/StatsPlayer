package net.gomblotto.configs;


import net.gomblotto.StatsCore;

import java.io.File;
import java.io.IOException;

public class StatsSQL implements ConfigService {
    private File file;

    @Override
    public void init() {
        this.file = new File(StatsCore.getInstance().getDataFolder(), "stats.db");
        this.create();
    }

    @Override
    public void saveConfig() {
    }

    @Override
    public void load() {
    }

    @Override
    public void reload() {
    }

    public void create() {
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public File getFile() {
        return this.file;
    }
}
