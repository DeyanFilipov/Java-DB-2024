package DBAppsIntroduction.Exercise;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Homework homework = new Homework();
        homework.setConnection("root", "Filipov5599");

        //homework.getVillainsNameEx2();
        //homework.getMinionNamesEx3();
        //homework.addMinionEx4();
        //homework.changeTownNameCastingEx5();
        homework.increaseAgeWithStoreProcedureEx9();

    }
}
