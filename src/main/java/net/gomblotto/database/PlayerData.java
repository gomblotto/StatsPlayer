package net.gomblotto.database;

import net.gomblotto.StatsCore;
import net.gomblotto.players.StatsPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerData  extends SQLite{
    public PlayerData(String path) throws ClassNotFoundException {
        super(path);
    }
    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Stats (name varchar(25) PRIMARY KEY, kills integer, deaths integer, ks integer);";
        try (PreparedStatement stmt = this.getConnection().prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insert(String player, int kills,int deaths, int ks) {
        try (PreparedStatement insert = this.getConnection().prepareStatement("INSERT INTO Stats VALUES(?,?,?,?)")) {
            insert.setString(1, player);
            insert.setInt(2, kills);
            insert.setInt(3, deaths);
            insert.setInt(4, ks);

            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void delete() {
        try (PreparedStatement insert = this.getConnection().prepareStatement("DELETE FROM Stats;")) {
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(String player, String data, int value) {
        try (PreparedStatement insert = this.getConnection().prepareStatement("UPDATE Stats SET "+data+"=? WHERE name=?")) {
            insert.setString(2, player);
            insert.setInt(1, value);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAll(String player, int kills, int deaths,int ks) {
            if(hasAccount(player)) {
                update(player, "kills", kills);
                update(player, "deaths", deaths);
                update(player, "ks", ks);
            }else{
                insert(player, kills,deaths,ks);
            }
    }

    private boolean hasAccount(String player) {
        try (PreparedStatement select = this.getConnection().prepareStatement("SELECT * FROM Stats WHERE name=?")) {
            select.setString(1, player);
            ResultSet result = select.executeQuery();
            boolean exists = result.next();
            result.close();
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadData(){
        try (PreparedStatement insert = this.getConnection().prepareStatement("SELECT * FROM Stats")) {
        ResultSet resultSet = insert.executeQuery();
        while (resultSet.next()){
            StatsPlayer player = new StatsPlayer(resultSet.getString("name"));
            player.setKills(resultSet.getInt("kills"));
            player.setDeaths(resultSet.getInt("deaths"));
            player.setMaxKS(resultSet.getInt("ks"));
            StatsCore.getInstance().getStatsPlayerManager().addStatsPlayer(player);
        }
        resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    }
