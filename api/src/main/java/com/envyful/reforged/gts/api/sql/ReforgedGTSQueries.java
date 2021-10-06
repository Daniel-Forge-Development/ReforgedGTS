package com.envyful.reforged.gts.api.sql;

public class ReforgedGTSQueries {

    public static final String CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS `reforged_gts_trade`(" +
            "id             INT             UNSIGNED    NOT NULL    AUTO_INCREMENT, " +
            "owner          VARCHAR(64)     NOT NULL, " +
            "ownerName      VARCHAR(16)     NOT NULL, " +
            "expiry         BIGINT          UNSIGNED    NOT NULL, " +
            "cost           DOUBLE          UNSIGNED    NOT NULL, " +
            "removed        INT             UNSIGNED    NOT NULL, " +
            "purchased      INT             UNSIGNED    NOT NULL, " +
            "type           VARCHAR(20)     NOT NULL, " +
            "content_type   VARCHAR(1)      NOT NULL, " +
            "contents   BLOB            NOT NULL, " +
            "PRIMARY KEY(id));";

    public static final String GET_ALL_ACTIVE = "SELECT owner, ownerName, expiry, cost, removed, type, content_type, " +
            "contents, purchased " +
            "FROM `reforged_gts_trade` " +
            "WHERE expiry > ? AND removed = 0;";

    public static final String GET_ALL_PLAYER = "SELECT owner, ownerName, expiry, cost, removed, type, content_type, " +
            "purchased, contents " +
            "FROM `reforged_gts_trade` " +
            "WHERE owner = ?;";

    public static final String UPDATE_PLAYER_NAME = "UPDATE `reforged_gts_trade` SET ownerName = ? WHERE owner = ?;";

    public static final String UPDATE_REMOVED = "UPDATE `reforged_gts_trade` " +
            "SET removed = ?, purchased = ? " +
            "WHERE owner = ? AND expiry = ? AND cost = ? AND content_type = ? AND type = ?;";

    public static final String ADD_TRADE = "INSERT INTO `reforged_gts_trade`" +
            "(owner, ownerName, expiry, cost, removed, type, content_type, contents) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String REMOVE_TRADE = "DELETE FROM `reforged_gts_trade` " +
            "WHERE owner = ? AND expiry = ? AND cost = ? AND content_type = ? AND type = ?;";

    public static final String UPDATE_OWNER = "UPDATE `reforged_gts_trade` " +
            "SET owner = ?, ownerName = ? " +
            "WHERE owner = ? AND expiry = ? AND cost = ? AND content_type = ? AND type = ?;";

}
