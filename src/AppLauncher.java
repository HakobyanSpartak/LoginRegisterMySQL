import DB.MyJDBC;
import GUIs.LoginFormGUI;
import GUIs.RegisterFormGUI;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // instantiate a LoginFormGUI  obj and make it visible
                new LoginFormGUI().setVisible(true);
//                new RegisterFormGUI().setVisible(false);

//                System.out.println(MyJDBC.checkUser("usernamedd"));

                // check register test
//                System.out.println(MyJDBC.register("usjan", "123456"));

                // check validate login test
                System.out.println(MyJDBC.validateLogin("username", "password"));
            }
        });
    }
}
