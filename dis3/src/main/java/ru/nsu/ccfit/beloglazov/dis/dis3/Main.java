package ru.nsu.ccfit.beloglazov.dis.dis3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nsu.ccfit.beloglazov.dis.dis3.db.ExecuteStrategy;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
//        CompressedOsmParser.parseWithLoading(
//                ExecuteStrategy.PREPARED_STATEMENT,
//                "RU-NVS.osm.bz2",
//                false,
//                10
//        );
        SpringApplication.run(Main.class, args);
    }
}