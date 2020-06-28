package net.gomblotto.configs;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {
    private final List<ConfigService> configServices;

    @Getter private final StatsSQL statsSQL;
    @Getter private final MessagesConfig messagesConfig;

    public ConfigManager() {
        this.configServices = new ArrayList<>();
        this.statsSQL = new StatsSQL();
        this.messagesConfig = new MessagesConfig();
    }

    public void loadConfig() {
        this.configServices.add(this.statsSQL);
        this.configServices.add(messagesConfig);
        this.configServices.forEach(ConfigService::init);
    }

    public void reloadConfig() {
        this.configServices.forEach(ConfigService::reload);
    }


}
