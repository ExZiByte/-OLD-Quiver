package nestedvar.Quiver;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.net.UnknownHostException;

public class Controller {
    @FXML
    private PasswordField bottoken;
    @FXML
    private TextField shardCount;
    @FXML
    Button startButton;
    @FXML
    Button restartButton;
    @FXML
    Button stopButton;
    static Quiver quiver;

    public void startBot() throws LoginException, RateLimitedException, UnknownHostException, InterruptedException {
        //System.out.print(Integer.parseInt(shardCount.getText()));
        quiver = new Quiver(bottoken.getText());
    }

    public void restartBot() throws LoginException, RateLimitedException, UnknownHostException, InterruptedException{
        quiver.destroy();
        quiver = new Quiver(bottoken.getText());
    }

    public void stopBot(){
        quiver.destroy();
    }

    public void testDB(){

    }

}
