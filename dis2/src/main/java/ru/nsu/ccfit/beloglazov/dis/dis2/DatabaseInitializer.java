package ru.nsu.ccfit.beloglazov.dis.dis2;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseInitializer {

    public static void initialize() throws SQLException, IOException {
        DatabaseConnection.create();
    }
}