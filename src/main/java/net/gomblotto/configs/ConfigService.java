package net.gomblotto.configs;

public interface ConfigService {
    void init();

    void saveConfig();

    void load();

    void reload();
}
